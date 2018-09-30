package com.miaoshasha.order.mapper;

import com.miaoshasha.common.base.BaseMapper;
import com.miaoshasha.common.entity.order.OrderProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderProductMapper extends BaseMapper<OrderProduct> {


    /**
     * 查询订单下的商品信息
     * @param orderId
     * @return
     */
    List<OrderProduct> selectProductByOrderId(@Param("orderId") Long orderId);
}