package com.miaoshasha.common.utils;

/**
 * 计数器
 * Created by fengchaojun on 2018/6/22.
 */
public class Counter {

    private static long cnt = 0;

    private Counter() {

    }

    private static volatile Counter counter;

    public static Counter getSingleton() {
        if (counter == null) {
            synchronized (Counter.class) {
                if (counter == null) {
                    counter = new Counter();
                }
            }
        }

        return counter;
    }

    public static long getCnt() {
        return cnt;
    }

    public static void addCnt() {
        cnt++;
    }

}
