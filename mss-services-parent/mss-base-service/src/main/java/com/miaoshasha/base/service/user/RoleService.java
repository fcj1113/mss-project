package com.miaoshasha.base.service.user;

import com.miaoshasha.common.base.BaseService;
import com.miaoshasha.common.entity.user.Role;


/**
 * Created by fengchaojun on 2018/5/11.
 */
public interface RoleService extends BaseService<Role> {


    /**
     * 获取用户的角色信息
     * @param userId
     * @return
     */
    Role getRoleByUserId(Long userId);
}
