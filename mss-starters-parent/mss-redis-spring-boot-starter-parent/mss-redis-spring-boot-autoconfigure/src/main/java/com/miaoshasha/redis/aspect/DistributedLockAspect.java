package com.miaoshasha.redis.aspect;

import com.miaoshasha.common.utils.AnnotationResolver;
import com.miaoshasha.redis.annotation.EnableLock;
import com.miaoshasha.redis.lock.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * Created by fengchaojun on 2018/6/20.
 */
@Aspect
@Configuration
public class DistributedLockAspect {

    private final Logger logger = LoggerFactory.getLogger(DistributedLockAspect.class);

    @Autowired
    private DistributedLock distributedLock;


    @Pointcut("@annotation(com.miaoshasha.redis.annotation.EnableLock)")
    private void pointcut(){
    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        EnableLock enableLock = method.getAnnotation(EnableLock.class);
        Object[] args = joinPoint.getArgs();
        String key = enableLock.value();
        key = AnnotationResolver.parse(key, method, args);
        int retryTimes = enableLock.action().equals(EnableLock.LockFailAction.CONTINUE) ? enableLock.retryTimes() : 0;
        boolean lock = distributedLock.lock(key, enableLock.keepMills(), retryTimes, enableLock.sleepMills());
        if(!lock) {
            logger.debug("get lock failed : " + key);
            return null;
        }

        //得到锁,执行方法，释放锁
        logger.debug("get lock success : " + key);
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            logger.error("execute locked method occured an exception", e);
        } finally {
            //执行完成，最终要释放锁
            boolean releaseResult = distributedLock.releaseLock(key);
            logger.debug("release lock : " + key + (releaseResult ? " success" : " failed"));
        }
        return null;
    }

}
