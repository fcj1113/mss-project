package com.miaoshasha.common.mq;

/**
 * 消息队列发送接口
 */
public interface MsgPublisher {

    /**
     * 发送消息到rabbitmq消息队列
     * @param message
     * @param exchangeName 交换机名称
     * @param queueName 队列名称
     * @param routingKey 路由键
     */
    public void send(Object message, String exchangeName, String queueName, String routingKey);
}
