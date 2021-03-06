package com.miaoshasha.common.command;


/**
 * 命令接口
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-06-12
 * Time：20:50
 * ================================
 */
public interface CommandCallBack<T> {

    /**
     * 获得锁执行方法
     * @throws Throwable
     */
    T obtain() throws Throwable ;

    /**
     * 未获得锁执行方法，执行其他业务流程
     * @throws Throwable
     */
    T notObtain() throws Throwable;

}
