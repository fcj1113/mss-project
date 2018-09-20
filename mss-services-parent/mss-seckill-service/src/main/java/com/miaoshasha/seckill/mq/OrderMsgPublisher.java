package com.miaoshasha.seckill.mq;

import com.miaoshasha.common.mq.MsgPublisher;
import com.miaoshasha.common.mq.QueueBindBuilder;
import com.miaoshasha.common.mq.RabbitConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fengchaojun on 2018/6/19.
 */
@Component
public class OrderMsgPublisher implements MsgPublisher {

    private Logger logger = LoggerFactory.getLogger(OrderMsgPublisher.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private QueueBindBuilder queueBindBuilder ;

    @Override
    public void send(Object message, String exchangeName, String queueName, String routingKey) {
            queueBindBuilder.declareBindingDirect(exchangeName,queueName,routingKey);
            CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
            //发送消息到消息队列
            rabbitTemplate.convertAndSend(exchangeName, routingKey,message,correlationId);
            logger.debug("[rabbitmq-sendMessage]queueName:{} ,uuid:{},msg:{}",queueName,correlationId.getId(),message);
    }


    /**
     *
     * @param message
     */
    public void send(Object message){
            this.send(message, RabbitConstants.ORDER_EXCHANGE_NAME,RabbitConstants.ORDER_PROMO_QUEUE_NAME, RabbitConstants.ORDER_PROMO_ROUTING_KEY);
    }

}
