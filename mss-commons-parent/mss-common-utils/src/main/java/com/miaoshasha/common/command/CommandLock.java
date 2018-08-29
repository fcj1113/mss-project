package com.miaoshasha.common.command;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 命令锁
 */
public class CommandLock {

    private static final ConcurrentHashMap<String,Object> commandData = new ConcurrentHashMap<>();

    private Object lock ;

    /**
     * 命令执行器
     * @return
     */
    public CommandExecutor commandExecutor(String key){
        Object lockObj = new Object();
        Object commandLockObj = commandData.putIfAbsent(key,lockObj);
        if(commandLockObj == null){//第一次获得锁
            lock = lockObj ;
            return new CommandExecutor(key,true,this);
        }else{
            lock = commandLockObj;
            return new CommandExecutor(key,false,this);
        }
    }


    /**
     * 释放key锁
     * @param key
     */
    public void release(String key){
        synchronized (lock){
            lock.notifyAll();
        }
        commandData.remove(key);
    }


    /**
     * 请求等待
     */
    public void waitCall(){
        synchronized (lock){
            try {
                lock.wait(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
