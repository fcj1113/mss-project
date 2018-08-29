package com.miaoshasha.order.service;

import com.miaoshasha.common.base.BaseService;
import com.miaoshasha.common.dto.order.PromoDTO;
import com.miaoshasha.common.entity.order.OrderInfo;
import com.miaoshasha.common.entity.order.OrderProduct;
import com.miaoshasha.common.entity.order.OrderSend;

/**
 * Created by fengchaojun on 2018/6/22.
 */
public interface OrderService extends BaseService<OrderInfo> {


    /**
     * 保存订单相关
     * @param orderInfo
     * @param orderProduct
     * @param orderSend
     * @return
     */
     long saveOrder(OrderInfo orderInfo, OrderProduct orderProduct, OrderSend orderSend);

    /**
     * 保存活动订单
     * @param promoDTO
     * @return
     */
     long savePromoOrder(PromoDTO promoDTO);

}
