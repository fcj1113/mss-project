package com.miaoshasha.redis.autoconfigure;

import com.miaoshasha.common.command.CommandCallBack;
import com.miaoshasha.common.command.CommandExecutor;
import com.miaoshasha.common.command.CommandLock;
import com.miaoshasha.redis.annotation.EnableCache;
import com.miaoshasha.redis.annotation.EnableCacheEvict;
import com.miaoshasha.redis.client.JedisClient;
import com.miaoshasha.redis.util.CacheHandler;
import com.miaoshasha.redis.util.ObjectHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存注解EnableCache的实现
 * Created by fengchaojun on 2018/6/29.
 */
@Slf4j
@Aspect
@Configuration
@ConditionalOnClass(JedisClient.class)
@EnableConfigurationProperties(RedisClusterProperties.class)
public class CacheAspect {

    @Autowired
    private JedisClient<Object> jedisClient;

    @Autowired
    private RedisClusterProperties redisClusterProperties;
    /**
     * 并发时等待超时时间。默认1秒
     */
    public static final long timeout = 100;


    private ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * 启用缓存的处理
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.miaoshasha.redis.annotation.EnableCache)")
    public Object cacheAround(ProceedingJoinPoint joinPoint) throws Throwable {

        //生成key
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        EnableCache enableCache = method.getAnnotation(EnableCache.class);
        String prefix = redisClusterProperties.getPrefix();
        String key = CacheHandler.genCacheKey(joinPoint, enableCache, prefix);

//        ObjectHolder objectHolder = getCache(key);
//        Object resultObj = objectHolder.getValue();
//
//        if (objectHolder.exist()) {
//            log.info("------------resultObj=" + resultObj + "-----------");
//            return resultObj;
//        } else if (resultObj == null && !enableCache.concurrent()) {
//            //缓存为空时，执行目标方法，并把返回结果存储到缓存中
//            return setCache(joinPoint, enableCache, key);
//        } else {//加锁，防止高并发穿透导致雪崩、
//            boolean acquired = false; //默认未持有锁
//            try {
//                acquired = reentrantLock.tryLock(timeout, TimeUnit.SECONDS);//超过等待时间，终止当前线程
//            } catch (InterruptedException ex) {
//                throw new SystemException(ErrorCode.CONCURRENT_CACHE_KEY_TIMEOUT);
//            }
//
//            try {
//                //通过双重锁判断
//                objectHolder = getCache(key);
//                if (!objectHolder.exist()) {
//                    log.info("-------进入缓存并发锁的执行过程----------");
//                    resultObj = setCache(joinPoint, enableCache, key);
//                }
//            } finally {
//                if (acquired)
//                    reentrantLock.unlock();
//            }
//
//            return resultObj.equals(EnableCache.EMPTY) ? null : resultObj;
//        }

        return cacheProcess(joinPoint, enableCache, key, new AtomicInteger(0));
    }


    public Object cacheProcess(ProceedingJoinPoint joinPoint, EnableCache enableCache, String key, AtomicInteger counter) throws Throwable {
        log.debug("------------counter=" + counter + "-----------");
        ObjectHolder objectHolder = getCache(key);
        Object resultObj = objectHolder.getValue();
        if (objectHolder.exist()) {
            return resultObj;
        } else if (resultObj == null && !enableCache.concurrent()) {
            //缓存为空时，执行目标方法，并把返回结果存储到缓存中
            return setCache(joinPoint, enableCache, key);
        } else {//加锁，防止高并发穿透导致雪崩、
            if (counter.incrementAndGet() > 5) {
                throw new RuntimeException("缓存并发错误");
            }
            CommandExecutor commandExecutor = new CommandLock().commandExecutor(key);
            resultObj = commandExecutor.run(new CommandCallBack<Object>() {

                @Override
                public Object obtain() throws Throwable {
                    return setCache(joinPoint, enableCache, key);
                }

                @Override
                public Object notObtain() throws Throwable {
                    return cacheProcess(joinPoint, enableCache, key, counter);
                }
            });

            return resultObj.equals(EnableCache.EMPTY) ? null : resultObj;
        }
    }


    /**
     * 保存结果
     *
     * @param joinPoint
     * @param enableCache
     * @throws Throwable
     */
    public Object setCache(ProceedingJoinPoint joinPoint, EnableCache enableCache, String key) throws Throwable {
        Object value = joinPoint.proceed();
        int expire = enableCache.expire();//缓存失效时间长
        if (value == null) {//没有失效时间时，默认null设置10秒的失效时间
            value = EnableCache.EMPTY;
            expire = expire == -1 ? 10 : expire;
        }

        if (expire == -1) {
            jedisClient.setEntity(key, value);
        } else {
            jedisClient.setEntity(key, expire, value);
        }

        return value;
    }


    public ObjectHolder getCache(String key) {
        return ObjectHolder.getInstance(jedisClient.getEntity(key));
    }

    /**
     * 在方法执行前清除缓存
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before(value = "@annotation(com.miaoshasha.redis.annotation.EnableCacheEvict)")
    public void cacheEvictBefore(JoinPoint joinPoint) throws Throwable {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        EnableCacheEvict enableCacheEvict = method.getAnnotation(EnableCacheEvict.class);
        String prefix = redisClusterProperties.getPrefix();

        //生成key的数组
        String[] keys = CacheHandler.genCacheKey(joinPoint, enableCacheEvict, prefix);

        jedisClient.delete(keys);

    }

}
