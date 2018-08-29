package com.miaoshasha.common.entity.order;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

/**
 *
 */
@Data
public class OrderProduct extends AbstractBaseEntity{
    /** */
    private Long pid;

    /** 订单id*/
    private Long orderId;

    /** */
    private Long productId;

    /** */
    private Long skuId;

    /** sku的实际销售价格，单位分*/
    private Long productPrice;

    /** 购买的sku数量*/
    private Integer quantity;


    @Override
    public Long getId() {
        return this.pid;
    }
}