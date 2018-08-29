package com.miaoshasha.redis.util;

import com.miaoshasha.common.utils.AnnotationResolver;
import com.miaoshasha.redis.annotation.EnableCache;
import com.miaoshasha.redis.annotation.EnableCacheEvict;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public final class CacheHandler {

    public static final String CACHE_SEPARATOR = ":";


    /**
     * 生成key，规则默认
     * prefix:cacheName:key
     * prefix:类名:方法名:key
     *
     * @param joinPoint
     * @param enableCache
     */
    public static String genCacheKey(JoinPoint joinPoint, EnableCache enableCache, String prefix) {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = method.getName();
        //key支持SPEL表达式
        String key = AnnotationResolver.parse(enableCache.key(), joinPoint);

        //cacheName为空时加入类和method名称的前缀
        String cacheName = StringUtils.isEmpty(enableCache.cacheName()) ?
                (className + CACHE_SEPARATOR + methodName + CACHE_SEPARATOR) :
                (enableCache.cacheName() + CACHE_SEPARATOR);

        return prefix + CACHE_SEPARATOR + cacheName + key;
    }


    /**
     * 生成key，规则默认
     * prefix:cacheName:key
     * prefix:类名:方法名:key
     *
     * @param joinPoint
     * @param enableCacheEvict
     */
    public static String[] genCacheKey(JoinPoint joinPoint, EnableCacheEvict enableCacheEvict, String prefix) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = method.getName();

        String clazzKey = className + CACHE_SEPARATOR + methodName + CACHE_SEPARATOR;

        String[] cacheNames = enableCacheEvict.cacheName();
        String[] keys = enableCacheEvict.key();

        String[] resKeys = null;
        if (keys != null && keys.length > 0) {
            resKeys = new String[keys.length];
            for (int i = 0; i < keys.length; i++) {
                String key = AnnotationResolver.parse(keys[i], joinPoint);
                String cacheName = "";
                if (cacheNames == null || cacheNames.length == 0) {
                    cacheName = clazzKey;
                } else {
                    if (i <= cacheNames.length) {
                        cacheName = cacheNames[i] + CACHE_SEPARATOR;
                    } else {
                        cacheName = clazzKey;
                    }
                }

                resKeys[i] = prefix + CACHE_SEPARATOR + cacheName + key;
            }

        } else {
            resKeys = new String[]{prefix + CACHE_SEPARATOR + clazzKey};
        }

        log.info("-------------keys{"+Arrays.toString(resKeys)+"}--------------");

        return resKeys;
    }
}
