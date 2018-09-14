package com.miaoshasha.ratelimiter.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 限流注解，支持应用的单实例API限流
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-14
 * Time：16:24
 * -----------------------------
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RateLimiter {

    /** 限流的key，类名+方法名 */
    String value() default "defaultKey";

    /** 每秒限制请求数*/
    double permitsPerSecond() default 80;

    /** 获取许可令牌的数量，默认是1 */
    int permits() default 1;

    /** 获取许可令牌超时时间，默认是0*/
    long timeout() default 0;

    /** 获取许可令牌超时时间单位，默认微秒*/
    TimeUnit timeUnit() default TimeUnit.MICROSECONDS;

}
