package com.miaoshasha.common.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * 消费者基类
 * Created by fengchaojun on 2018/6/26.
 */
public class QueueConsumer {

    /**
     * 消费者
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        Queue queue = new Queue("myqueue", true, false, false, null);
        container.setQueues(queue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {

                byte[] body = message.getBody();
                try {
                    //业务逻辑
                    //logger.info("消费 receive msg : " + new String(body));
                    // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
                    //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //手动确认确认消息成功消费
                } catch (Exception e) {
                    //logger.info("消费失败: " + new String(body));
                    // ack返回false，并重新回到队列，api里面解释得很清楚
                    try {
                        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
        return container;
    }
}
