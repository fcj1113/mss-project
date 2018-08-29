package com.miaoshasha.common.entity.product;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

@Data
public class ProductAttrItem extends AbstractBaseEntity{
    /** 属性项ID*/
    private Long itemId;

    /** 属性ID*/
    private Long attrId;

    /** 属性项名称*/
    private String itemName;

    /** 值别名*/
    private String alias;

    /** 状态*/
    private Integer state;

    /** 排序*/
    private Integer seqNo;


    @Override
    public Long getId() {
        return this.itemId;
    }
}