package com.miaoshasha.base.service.user.impl;

import com.miaoshasha.base.mapper.user.RoleMapper;
import com.miaoshasha.base.service.user.RoleService;
import com.miaoshasha.common.base.AbstractBaseService;
import com.miaoshasha.common.entity.user.Role;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.exception.SystemException;
import com.miaoshasha.redis.annotation.EnableCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fengchaojun on 2018/5/15.
 */
@Service
public class RoleServiceImpl extends AbstractBaseService<RoleMapper,Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @EnableCache(key="#userId",expire = 5)
    public Role getRoleByUserId(Long userId) {
        Role role = roleMapper.getRoleByUserId(userId);
        if (role == null) {
           throw new SystemException(ErrorCode.DATA_NULL_ERROR, "用户不存在");
        }
        return roleMapper.getRoleByUserId(userId);
    }
}
