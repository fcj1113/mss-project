package com.miaoshasha.common.entity.product;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

@Data
public class ProductCategory extends AbstractBaseEntity {

    /** 主键，uuid*/
    private Long categoryId;

    /** 类别名称*/
    private String categoryName;

    /** 父类别，若无则为0*/
    private Long parentId;

    /** 简介*/
    private String intro;

    /** 图标URL*/
    private String categoryIcon;

    /** 状态*/
    private Boolean state;

    /** 顺序*/
    private Integer seqNo;

    @Override
    public Long getId() {
        return this.categoryId;
    }
}