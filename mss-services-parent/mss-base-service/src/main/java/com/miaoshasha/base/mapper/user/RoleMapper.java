package com.miaoshasha.base.mapper.user;

import com.miaoshasha.common.base.BaseMapper;
import com.miaoshasha.common.entity.user.Role;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取用户的角色信息
     * @param userId
     * @return
     */
    Role getRoleByUserId(Long userId);
}