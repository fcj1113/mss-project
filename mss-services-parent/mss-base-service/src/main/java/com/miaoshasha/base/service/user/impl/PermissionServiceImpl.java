package com.miaoshasha.base.service.user.impl;

import com.miaoshasha.base.mapper.permission.PermissionResourceMapper;
import com.miaoshasha.base.service.user.PermissionService;
import com.miaoshasha.base.service.user.RoleService;
import com.miaoshasha.common.base.AbstractBaseService;
import com.miaoshasha.common.entity.permission.PermissionResource;
import com.miaoshasha.common.entity.user.Role;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl
        extends AbstractBaseService<PermissionResourceMapper,PermissionResource> implements PermissionService {

    @Autowired
    private RoleService roleService ;

    @Override
    public List<PermissionResource> findByRole(Long roleId, Integer resType) {
        return mapper.findByRole(roleId, resType);
    }

    @Override
    public List<PermissionResource> findByUserId(Long userId) {
        Role role = roleService.getRoleByUserId(userId);
        if(role == null){
            throw new SystemException(ErrorCode.DATA_NULL_ERROR,"无角色用户");
        }
        return mapper.findByRole(role.getRoleId(), null);
    }


}
