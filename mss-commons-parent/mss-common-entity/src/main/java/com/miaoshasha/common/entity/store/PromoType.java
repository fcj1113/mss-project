package com.miaoshasha.common.entity.store;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

/**
 *
 */
@Data
public class PromoType extends AbstractBaseEntity {
    private static final long serialVersionUID = 3132663661595522509L;
    /** */
    private Long pid;

    /** 活动类型名称*/
    private String typeName;

    /** 活动分类的图标*/
    private String typeIcon;

    /** 备注*/
    private String notes;

    /** 状态*/
    private Integer state;


    @Override
    public Long getId() {
        return this.pid;
    }
}