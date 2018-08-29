package com.miaoshasha.base.service.user;

import com.miaoshasha.common.base.BaseService;
import com.miaoshasha.common.entity.permission.PermissionResource;

import java.util.List;

public interface PermissionService extends BaseService<PermissionResource> {


    /**
     * 根据角色获取权限资源
     * @param roleId
     * @param resType
     *
     * @return
     */
    List<PermissionResource> findByRole(Long roleId, Integer resType);

    /**
     * 根据用户id获取权限信息
     * @param userId
     * @return
     */
    List<PermissionResource> findByUserId(Long userId);
}
