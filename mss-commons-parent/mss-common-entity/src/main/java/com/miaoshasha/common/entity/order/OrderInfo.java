package com.miaoshasha.common.entity.order;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

/**
 *
 */
@Data
public class OrderInfo extends AbstractBaseEntity {
    /** 主键，订单号*/
    private Long orderId;

    /** */
    private String orderNo;

    /** 店铺ID*/
    private Long storeId;

    /** 用户ID*/
    private Long memberId;

    /** 支付方式 1-货到付款，2-网银，3-支付宝，4-微信，5-充值卡抵扣（预留）*/
    private Integer payType;

    /** 订单总价，单位分*/
    private Long totalAmount;

    /** 抵扣金额，单位分*/
    private Long dedAmount;

    /** 实际支付金额，单位分*/
    private Long payAmount;

    /** 优惠金额，单位分*/
    private Long discountAmount;

    /** 使用积分数量*/
    private Long point;

    /** 使用的卡券ID*/
    private Long voucherId;

    /** 订单流转状态 */
    private Integer state;

    /** 是否要发票，0-是，1-不是*/
    private Boolean isInvoice;

    /** 送货方式，1-自取，2-送货上门*/
    private Integer sendType;

    /** 是否支付 0-未支付，1-支付*/
    private Boolean isPay;

    /** 来源*/
    private Integer source;

    /** 备注*/
    private String notes;


    @Override
    public Long getId() {
        return this.orderId;
    }
}