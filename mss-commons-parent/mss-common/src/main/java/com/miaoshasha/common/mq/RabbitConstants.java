package com.miaoshasha.common.mq;

/**
 * Created by fengchaojun on 2018/6/22.
 */
public class RabbitConstants {

    /** 日志 **/
    //操作日志交换机名称
    public static final String LOG_EXCHANGE_NAME = "log.direct.exchange";
    //操作日志队列名称
    public static final String OP_LOG_QUEUE_NAME = "oplog.queue";
    //操作日志路由key
    public static final String OP_LOG_ROUTING_KEY = "oplog.routing";

    //网关请求日志
    public static final String GATEWAY_LOG_QUEUE_NAME="gateway.log.queue.name";
    //网关请求日志路由key
    public static final String GATEWAY_LOG_ROUTING_KEY="gateway.log.routing.key";

    /** 订单 **/
    //订单交换机名称
    public static final String ORDER_EXCHANGE_NAME = "order.direct.exchange";
    //订单队列名称
    public static final String ORDER_QUEUE_NAME = "order.queue";
    //订单路由key
    public static final String ORDER_ROUTING_KEY = "order.routing";

    //优惠活动订单队列名称
    public static final String ORDER_PROMO_QUEUE_NAME = "order.promo.queue";
    //优惠活动订单路由key
    public static final String ORDER_PROMO_ROUTING_KEY = "order.promo.routing";

}
