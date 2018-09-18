package com.miaoshasha.order.service.impl;

import com.miaoshasha.common.base.AbstractBaseService;
import com.miaoshasha.common.dto.order.PromoDTO;
import com.miaoshasha.common.entity.order.OrderInfo;
import com.miaoshasha.common.entity.order.OrderProduct;
import com.miaoshasha.common.entity.order.OrderSend;
import com.miaoshasha.order.mapper.OrderInfoMapper;
import com.miaoshasha.order.service.OrderProductService;
import com.miaoshasha.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fengchaojun on 2018/6/22.
 */
@Slf4j
@Service
public class OrderServiceImpl extends AbstractBaseService<OrderInfoMapper, OrderInfo> implements OrderService {

    @Autowired
    private OrderProductService orderProductService ;

    @Transactional
    @Override
    public long saveOrder(OrderInfo orderInfo, OrderProduct orderProduct, OrderSend orderSend) {

        this.save(orderInfo);

        return orderInfo.getOrderId();
    }

    @Transactional
    @Override
    public long savePromoOrder(PromoDTO promoDTO) {
        OrderInfo orderInfo = promoDTO.getOrderInfo();
        log.debug("订单号：" + orderInfo.getOrderId());
        if(this.findById(orderInfo.getOrderId()) != null){
            log.debug("重复订单，ORDER_ID:"+orderInfo.getOrderId());
            return orderInfo.getOrderId();
        }
        this.save(orderInfo);
        //保存商品信息
        orderProductService.save(promoDTO.getOrderProduct());
        return orderInfo.getOrderId();
    }
}
