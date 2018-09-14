package com.miaoshasha.ratelimiter.autoconfigure;

import com.google.common.util.concurrent.RateLimiter;
import com.miaoshasha.common.command.CommandCallBack;
import com.miaoshasha.common.command.CommandLock;
import com.miaoshasha.ratelimiter.annotation.ApiRateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-14
 * Time：17:07
 * -----------------------------
 */
@Slf4j
@Aspect
@Configuration
@ConditionalOnClass(RateLimiter.class)
public class RateLimiterAspect {

    private Map<String, RateLimiter> rateLimiterMap = new HashMap<>();


    @Pointcut("@annotation(com.miaoshasha.ratelimiter.annotation.ApiRateLimiter)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        ApiRateLimiter apiRateLimiter = methodSignature.getMethod().getAnnotation(ApiRateLimiter.class);

        String key = apiRateLimiter.value();//todo，key策略待定制
        double permitsPerSecond = apiRateLimiter.permitsPerSecond();

        RateLimiter rateLimiter = rateLimiterMap.get(apiRateLimiter.value());
        if (rateLimiter == null) {
            rateLimiter = rateLimiterProcess(key,permitsPerSecond,new AtomicInteger(0));
        }
        if (rateLimiter.tryAcquire(apiRateLimiter.permits(), apiRateLimiter.timeout(), apiRateLimiter.timeUnit())) {
            try {
                return pjp.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    /**
     * 高并发时获取限流对象需要同步
     * @param key
     * @param permitsPerSecond
     * @param counter
     * @return
     * @throws Throwable
     */
    public RateLimiter rateLimiterProcess(String key, double permitsPerSecond ,AtomicInteger counter) throws Throwable {

        //循环超过10次,即等待500毫秒，超时
        if (counter.incrementAndGet() > 10) {
            throw new RuntimeException("系统限流对象超时");
        }

        return new CommandLock().commandExecutor(key).run(new CommandCallBack<RateLimiter>() {

            @Override
            public RateLimiter doIn() throws Throwable {
                RateLimiter rateLimiter = RateLimiter.create(permitsPerSecond) ;
                rateLimiterMap.put(key,rateLimiter);
                return rateLimiter;
            }

            @Override
            public RateLimiter doOut() throws Throwable {
                return rateLimiterProcess(key,permitsPerSecond,counter);
            }
        });

    }
}
