package com.miaoshasha.common.entity.store;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

@Data
public class PromoInfo extends AbstractBaseEntity {
    private static final long serialVersionUID = 6309968583253486561L;
    /** */
    private Long promoId;

    /** 促销名称*/
    private String promoName;

    /** */
    private String promoIcon;

    /** 促销类别ID*/
    private Long promoType;

    /** 产品id*/
    private Long productId;

    /** sku*/
    private Long skuId;

    /** 促销发起店铺ID*/
    private Long storeId;

    /** 开始时间*/
    private Long beginTime;

    /** 结束时间*/
    private Long endTime;

    /** 促销价格，单位分*/
    private Long promoPrice;

    /** 是否需要申请*/
    private Boolean isApply;

    /** 库存数量*/
    private Integer stockQuantity;

    /** 销售数量*/
    private Integer sellQuantity;

    /** 限购数量*/
    private Integer limitQuantity;

    /** 活动状态*/
    private Integer state;

    @Override
    public Long getId() {
        return this.promoId;
    }
}