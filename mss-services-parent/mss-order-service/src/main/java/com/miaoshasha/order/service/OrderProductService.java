package com.miaoshasha.order.service;

import com.miaoshasha.common.base.BaseService;
import com.miaoshasha.common.entity.order.OrderProduct;

import java.util.List;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-18
 * Time：18:51
 * -----------------------------
 */
public interface OrderProductService extends BaseService<OrderProduct> {

    /**
     * 查询订单下的商品信息
     * @param orderId
     * @return
     */
    List<OrderProduct> findProductByOrderId(Long orderId);
}
