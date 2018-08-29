package com.miaoshasha.order.mq;

import com.alibaba.fastjson.JSON;
import com.miaoshasha.common.dto.order.PromoDTO;
import com.miaoshasha.common.mq.RabbitConstants;
import com.miaoshasha.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fengchaojun on 2018/6/22.
 */
@Slf4j
@Component
@RabbitListener(bindings = {
        @QueueBinding(value =
        @Queue(value = RabbitConstants.ORDER_PROMO_QUEUE_NAME, durable = "true", autoDelete = "false", exclusive = "false"),
                exchange = @Exchange(value = RabbitConstants.ORDER_EXCHANGE_NAME,durable = "true"))})
public class OrderReceiver {

    @Autowired
    private OrderService orderService ;

    @RabbitHandler
    public void process(PromoDTO promoDTO) {
        log.debug("队列内容："+ JSON.toJSONString(promoDTO));
        orderService.savePromoOrder(promoDTO);
    }
}
