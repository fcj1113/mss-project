package com.miaoshasha.common.dto.order;

import com.miaoshasha.common.entity.order.OrderInfo;
import com.miaoshasha.common.entity.order.OrderProduct;
import com.miaoshasha.common.entity.order.OrderSend;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-30
 * Time：16:13
 * -----------------------------
 */
@Data
public class OrderDTO implements Serializable {

    private OrderInfo orderInfo ;

    private List<OrderProduct> orderProductList;

    private OrderSend orderSend ;
}
