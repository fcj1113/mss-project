package com.miaoshasha.common.mq;

import com.miaoshasha.common.enums.ExchangeType;
import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Rabbit队列动态绑定配置
 * Created by fengchaojun on 2018/6/25.
 */
@Component
public class QueueBindBuilder {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * 动态声明DIRECT转换器和queue的绑定关系
     *
     * @param exchangeName
     * @param queueName
     * @param routingKey
     */
    public void declareBinding(String exchangeName, ExchangeType exchangeType, String queueName, String routingKey) {
        if (rabbitAdmin.getQueueProperties(queueName) == null) {
            /*  queue 队列声明
            durable=true,交换机持久化,rabbitmq服务重启交换机依然存在,保证不丢失; durable=false,相反
            auto-delete=true:无消费者时，队列自动删除; auto-delete=false：无消费者时，队列不会自动删除
            排他性，exclusive=true:首次申明的connection连接下可见; exclusive=false：所有connection连接下*/
            Queue queue = new Queue(queueName, true, false, false, null);
            rabbitAdmin.declareQueue(queue);
            Binding binding = null;

            switch (exchangeType){
                case DIRECT:
                    DirectExchange directExchange = new DirectExchange(exchangeName);
                    rabbitAdmin.declareExchange(directExchange);
                    binding = BindingBuilder.bind(queue).to(directExchange).with(routingKey);    //将queue绑定到exchange
                    break;
                case TOPIC:
                    TopicExchange topicExchange = new TopicExchange(exchangeName);
                    rabbitAdmin.declareExchange(topicExchange);
                    binding = BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
                    break;
                case FANOUT:
                    FanoutExchange fanoutExchange = new FanoutExchange(exchangeName);
                    rabbitAdmin.declareExchange(fanoutExchange);
                    binding = BindingBuilder.bind(queue).to(fanoutExchange);
                    break;
            }

            rabbitAdmin.declareBinding(binding);
        }
//        else {
//            rabbitAdmin.getRabbitTemplate().setQueue(queueName);
//            rabbitAdmin.getRabbitTemplate().setExchange(exchangeName);
//            rabbitAdmin.getRabbitTemplate().setRoutingKey(routingKey);
//        }
    }


    /**
     * DirectExchange
     * @param exchangeName
     * @param queueName
     * @param routingKey
     */
    public void declareBindingDirect(String exchangeName, String queueName, String routingKey) {
        this.declareBinding(exchangeName,ExchangeType.DIRECT, queueName, routingKey);
    }


    /**
     * DirectExchange
     * queueName与routingKey保持一致
     *
     * @param directExchangeName
     * @param queueName
     */
    public void declareBindingDirect(String directExchangeName, String queueName) {
        String routingKey = queueName;
        this.declareBinding(directExchangeName,ExchangeType.DIRECT, queueName, routingKey);
    }


    /**
     * topicExchangeName
     *
     * @param topicExchangeName
     * @param queueRoutingkey
     */
    public void declareBindingTopic(String topicExchangeName,  Map<String ,String> queueRoutingkey ) {
        for (Map.Entry<String,String> entry : queueRoutingkey.entrySet()){
            String queueName = entry.getKey();
            String routingKey = entry.getValue();
            this.declareBinding(topicExchangeName,ExchangeType.TOPIC, queueName, routingKey);
        }
    }





    /**
     * 删除队列
     *
     * @param queueName
     */
    public void deleteQueue(String queueName) {
        rabbitAdmin.deleteQueue(queueName);
    }


    /**
     * 主题模式的数据类
     */
    @Data
    class TopicDeclare {
        /**
         * 转换器名称
         */
        private String exchangeName;

        /**
         * 队列与RoutingKey的绑定关系
         * key：队列名称，value：Routingkey名称
         */
        private Map<String ,String> queueRoutingkey ;
        /**
         * 发送消息的路由键
         */
        private String sendRoutingkey ;
    }
}
