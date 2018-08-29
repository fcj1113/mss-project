package com.miaoshasha.common.entity.product;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

@Data
public class ProductAttrRel extends AbstractBaseEntity{
    /** */
    private Long pid;

    /** */
    private Long productId;

    /** 属性ID*/
    private Long attrId;

    /** 属性值ID*/
    private Long itemId;

    /** 是否销售属性；0否，1-是*/
    private Boolean isSale;

    /** 自定义属性值*/
    private String attrValue;

    /** 图片路径*/
    private String attrImage;


    public Long getId() {
        return pid;
    }
}