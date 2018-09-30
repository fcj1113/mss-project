package com.miaoshasha.common.api.order;

import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.dto.order.OrderDTO;
import com.miaoshasha.common.dto.order.PromoDTO;
import com.miaoshasha.common.entity.order.OrderInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-30
 * Time：14:10
 * -----------------------------
 */
@RequestMapping("/order")
public interface OrderControllerApi {

    /**
     * 根据主键查询订单基本信息
     * @param orderId
     * @return
     */
    @RequestMapping("/findById")
    DataResult<OrderInfo> findById(@PathVariable Long orderId);

    /**
     * 查询订单详细信息
     * @param orderId
     * @return
     */
    @RequestMapping("/findOrderByOrderId")
    DataResult<OrderDTO> findOrderByOrderId(@PathVariable Long orderId);
}
