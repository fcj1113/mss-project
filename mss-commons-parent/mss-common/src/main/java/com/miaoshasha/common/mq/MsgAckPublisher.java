package com.miaoshasha.common.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by fengchaojun on 2018/6/25.
 */
public interface MsgAckPublisher extends RabbitTemplate.ConfirmCallback,MsgPublisher {

    /**
     * 发送消息到rabbitmq消息队列
     * @param message
     */
    public void send(Object message);

}
