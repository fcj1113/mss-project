package com.miaoshasha.common.command;

public class CommandExecutor {

    private String key ;
    private boolean acquireLock ;
    private CommandLock commandLock;

    /**
     *
     * @param key 命令的key
     * @param acquireLock 获得锁的标识
     */
    public CommandExecutor(String key,boolean acquireLock,CommandLock commandLock){
        this.key = key;
        this.acquireLock = acquireLock;
        this.commandLock = commandLock;
    }

    public boolean isAcquireLock() {
        return acquireLock;
    }

    public <T> T run(CommandCallBack<T> commandCallBack) throws Throwable {
        if(isAcquireLock()){//执行命令
            try {
                return commandCallBack.doIn();
            }finally {
                commandLock.release(key);
            }
        }else{
            //先等待
            commandLock.waitCall();
            return commandCallBack.doOut();
        }
    }


}
