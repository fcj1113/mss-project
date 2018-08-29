package com.miaoshasha.redis.lock;

/**
 * Created by fengchaojun on 2018/6/20.
 */
public interface DistributedLock {

    public static final long TIMEOUT_MILLIS = 30000;

    public static final int RETRY_TIMES = Integer.MAX_VALUE;

    public static final long SLEEP_MILLIS = 500;

    /**
     *
     * @param key
     * @param expire 超时时间
     * @param retryTimes 重试次数
     * @param sleepMillis 重试等待时间
     * @return
     */
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis);


    public boolean lock(String key);

    public boolean lock(String key, int retryTimes);

    public boolean lock(String key, int retryTimes, long sleepMillis);

    public boolean lock(String key, long expire);

    public boolean lock(String key, long expire, int retryTimes);

    public boolean releaseLock(String key);
}
