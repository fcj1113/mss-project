package com.miaoshasha.gateway.entity;

import com.google.common.util.concurrent.RateLimiter;
import com.miaoshasha.common.enums.ErrorCode;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-13
 * Time：15:50
 * -----------------------------
 */

@Data
public class BaseLimiterEntity implements Serializable {
    private static final long serialVersionUID = -7356348264784123089L;

    /**
     * 每秒限制的请求数
     */
    private double permitsPerSecond;

    /**
     * 获取许可数量，默认1
     */
    private int permits = 1;

    /**
     * 获取许可超时时间，默认0
     */
    private long timeout = 0;

    /**
     * 获取许可超时时间单位，默认MICROSECONDS
     * 取值：NANOSECONDS，MICROSECONDS，MILLISECONDS，MINUTES,HOURS,DAYS
     */
    private String timeUnit = "MICROSECONDS";


    /**
     * 超过限流时的错误码
     */
    private int errorCode = ErrorCode.SYSTEM_LIMITER.getCode();

    /**
     * 超过限流时的错误信息
     */
    private String errorMsg =  ErrorCode.SYSTEM_LIMITER.getMsg();


    /**
     * 是否启用限流
     */
    private boolean limiterEnabled;

    /**
     * 创建用户ID
     */
    private Long createUser ;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新用户ID
     */
    private Long updateUser ;

    /**
     * 更新时间
     */
    private Date updateTime;

    private RateLimiter rateLimiter;

    public RateLimiter rateLimiter() {
        if (rateLimiter == null) {
            synchronized (this){
                if(rateLimiter == null){
                    this.rateLimiter = RateLimiter.create(this.permitsPerSecond);
                    return rateLimiter;
                }
            }
        }

        return rateLimiter;
    }

}
