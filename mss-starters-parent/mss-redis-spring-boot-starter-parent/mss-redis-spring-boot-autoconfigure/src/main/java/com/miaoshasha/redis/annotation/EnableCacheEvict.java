package com.miaoshasha.redis.annotation;

import java.lang.annotation.*;

/**
 * 清除缓存
 * Created by fengchaojun on 2018/6/28.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EnableCacheEvict {

    /**
     * 缓存命名空间
     * @return
     */
    String[] cacheName() default {};

    /**
     * 缓存的key值，下标与缓存命名空间下标对应组合到一起
     * @return
     */
    String[] key() default {};


}
