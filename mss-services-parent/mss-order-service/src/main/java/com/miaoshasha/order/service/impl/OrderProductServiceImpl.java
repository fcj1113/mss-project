package com.miaoshasha.order.service.impl;

import com.miaoshasha.common.base.AbstractBaseService;
import com.miaoshasha.common.entity.order.OrderProduct;
import com.miaoshasha.order.mapper.OrderProductMapper;
import com.miaoshasha.order.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-18
 * Time：18:52
 * -----------------------------
 */
@Service
public class OrderProductServiceImpl extends AbstractBaseService<OrderProductMapper,OrderProduct> implements OrderProductService {

    @Autowired
    private OrderProductMapper orderProductMapper ;

    @Override
    public List<OrderProduct> findProductByOrderId(Long orderId) {
        return orderProductMapper.selectProductByOrderId(orderId);
    }
}
