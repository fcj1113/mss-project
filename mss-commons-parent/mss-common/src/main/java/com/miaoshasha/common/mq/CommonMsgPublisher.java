package com.miaoshasha.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 通用的消息发布，支持动态绑定交换机，队列，无需在config
 * Created by fengchaojun on 2018/7/27.
 */
@Slf4j
@Component
public class CommonMsgPublisher implements MsgPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private QueueBindBuilder queueBindBuilder;

    @Override
    public void send(Object message, String exchangeName, String queueName, String routingKey) {
        //动态绑定，无需在进行交换机绑定队列
        queueBindBuilder.declareBindingDirect(exchangeName, queueName, routingKey);
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //发送消息到消息队列
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message, correlationId);
        log.debug("[rabbitmq-sendMessage]queueName:{} ,uuid:{},msg:{}", queueName, correlationId.getId(), message);
    }

}
