package com.miaoshasha.common.command;

/**
 * 命令接口
 */
public interface CommandCallBack<T> {

    /**
     * 执行命令
     * @throws Throwable
     */
    T doIn() throws Throwable ;

    /**
     * 不进入执行命令时，执行其他业务流程
     * @throws Throwable
     */
    T doOut() throws Throwable;

}
