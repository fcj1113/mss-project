package com.miaoshasha.common.entity.permission;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

@Data
public class PermissionRole extends AbstractBaseEntity {

    private Long pid;

    private Long roleId;

    private Long resId;


    @Override
    public Long getId() {
        return this.pid;
    }
}