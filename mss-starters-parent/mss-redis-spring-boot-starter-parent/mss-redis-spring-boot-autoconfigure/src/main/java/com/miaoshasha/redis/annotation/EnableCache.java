package com.miaoshasha.redis.annotation;

import java.lang.annotation.*;

/**
 * 缓存数据注解
 * Created by fengchaojun on 2018/6/28.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EnableCache {

    /**
     * 缓存命名空间，默认是"类名:方法名"
     * @return
     */
    String cacheName() default "";

    /**
     * 缓存的key值，支持SPEL表达式
     * @return
     */
    String key() default "";

    /**
     * 过期时间（单位：秒），-1表示不设置缓存时间
     * @return
     */
    int expire() default -1 ;


    /**
     * 启用并发处理，防止缓存雪崩，启用后会影响部分性能，慎重使用；
     * @return
     */
    boolean concurrent() default false;

    /**
     * 当数据为null时，设置数据为<NULL>，防止高并发时大批量请求数据库，null的默认失效时间为10s
     */
    String EMPTY = "<NULL>";
}
