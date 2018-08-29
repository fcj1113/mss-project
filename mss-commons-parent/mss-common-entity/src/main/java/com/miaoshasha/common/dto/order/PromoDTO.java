package com.miaoshasha.common.dto.order;

import com.miaoshasha.common.entity.order.OrderInfo;
import com.miaoshasha.common.entity.order.OrderProduct;
import com.miaoshasha.common.entity.order.OrderSend;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by fengchaojun on 2018/6/15.
 */
@Data
public class PromoDTO implements Serializable {

    private static final long serialVersionUID = 3002574925363024015L;

    /**
     * 用户id
     */
    private Long userId ;

    /**
     * 活动id
     */
    private Long promoId;

    /**
     * 订单信息
     */
    private OrderInfo orderInfo ;

    /**
     * 当前订单的商品信息
     */
    private OrderProduct orderProduct ;

    /**
     * 当前订单的配送信息
     */
    private OrderSend orderSend ;

}
