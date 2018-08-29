package com.miaoshasha.base.mapper.permission;

import com.miaoshasha.common.base.BaseMapper;
import com.miaoshasha.common.entity.permission.PermissionResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限资源
 */
public interface PermissionResourceMapper extends BaseMapper<PermissionResource> {


    /**
     * 根据角色获取权限资源
     * @param roleId
     * @param resType
     *
     * @return
     */
    List<PermissionResource> findByRole(@Param("roleId") Long roleId, @Param("resType") Integer resType);



}