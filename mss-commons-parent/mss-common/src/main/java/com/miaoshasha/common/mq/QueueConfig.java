package com.miaoshasha.common.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

/**
 * Created by fengchaojun on 2018/5/22.
 */
public interface QueueConfig {

    /**
     * 配置路由交换对象实例
     * @return
     */
    DirectExchange directExchange();

    /**
     * 配置用户注册队列对象实例,并设置持久化队列
     * @return
     */
    Queue createQueue();

    /**
     * 将队列绑定到路由交换配置上并设置指定路由键进行转发
     * @return
     */
    Binding  bind();
}
