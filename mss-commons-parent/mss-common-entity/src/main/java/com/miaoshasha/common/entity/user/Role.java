package com.miaoshasha.common.entity.user;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

@Data
public class Role  extends AbstractBaseEntity {
    /** */
    private Long roleId;

    /** 角色编码*/
    private String roleCode;

    /** 上级角色*/
    private Long parentId;

    /** 角色名称*/
    private String roleName;

    /** 角色描述*/
    private String roleDesc;

    /** 角色状态*/
    private Integer state;

    @Override
    public Long getId() {
        return this.roleId;
    }
}