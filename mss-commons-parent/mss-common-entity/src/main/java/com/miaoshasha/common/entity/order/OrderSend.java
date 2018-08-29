package com.miaoshasha.common.entity.order;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class OrderSend extends AbstractBaseEntity{
    /** */
    private Long pid;

    /** 订单id*/
    private Long orderId;

    /** 会员默认地址id*/
    private Long addressId;

    /** 配送日期*/
    private Date deliveryDate;

    /** 快递公司名*/
    private String express;

    /** 快递单号*/
    private String expressNo;

    @Override
    public Long getId() {
        return this.pid;
    }
}