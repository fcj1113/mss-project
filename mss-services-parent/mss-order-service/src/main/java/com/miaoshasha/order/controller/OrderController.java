package com.miaoshasha.order.controller;

import com.miaoshasha.common.api.order.OrderControllerApi;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.dto.order.OrderDTO;
import com.miaoshasha.common.dto.order.PromoDTO;
import com.miaoshasha.common.entity.order.OrderInfo;
import com.miaoshasha.common.entity.order.OrderProduct;
import com.miaoshasha.order.service.OrderProductService;
import com.miaoshasha.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-30
 * Time：14:07
 * -----------------------------
 */
@RestController
public class OrderController implements OrderControllerApi {

    @Autowired
    private OrderService orderService ;

    @Autowired
    private OrderProductService orderProductService ;

    @Override
    public DataResult<OrderInfo> findById(Long orderId) {

        return DataResult.success(orderService.findById(orderId));
    }

    @Override
    public DataResult<OrderDTO> findOrderByOrderId(Long orderId) {
        OrderDTO orderDTO = new OrderDTO();
        OrderInfo orderInfo = orderService.findById(orderId) ;
        List<OrderProduct> orderProduct = orderProductService.findProductByOrderId(orderId);

        orderDTO.setOrderInfo(orderInfo);
        orderDTO.setOrderProductList(orderProduct);
        return DataResult.success(orderDTO);
    }
}
