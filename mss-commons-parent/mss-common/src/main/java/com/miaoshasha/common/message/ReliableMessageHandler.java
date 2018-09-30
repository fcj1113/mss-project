package com.miaoshasha.common.message;

import com.miaoshasha.common.entity.message.ReliableMessage;

import java.util.Map;

/**
 * 分布式事物-可靠消息最终一致性，若使用分布式事物，必须实现此接口
 * 主要功能：消息状态确认子系统 ， 消息恢复子系统
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-29
 * Time：18:02
 * -----------------------------
 */
public interface ReliableMessageHandler {


    /**
     * 处理[WAIT_VERIFY]状态的消息
     * @param reliableMessageMap
     */
    public void handleWaitingConfirmTimeOutMessages();


    /**
     * 处理[SENDING]状态的消息
     *
     * @param reliableMessageMap
     */
    public void handleSendingTimeOutMessages();
}
