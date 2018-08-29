package com.miaoshasha.common.entity.store;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

/**
 *
 */
@Data
public class StoreDetail  extends AbstractBaseEntity {
    /** */
    private Long pid;

    /** 店铺ID*/
    private Long storeId;

    /** 详情*/
    private String detail;

    @Override
    public Long getId() {
        return this.pid;
    }
}