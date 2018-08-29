package com.miaoshasha.base.service.user.impl;

import com.miaoshasha.base.mapper.user.UserMapper;
import com.miaoshasha.base.service.user.UserService;
import com.miaoshasha.common.base.AbstractBaseService;
import com.miaoshasha.common.entity.user.User;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.enums.LoginType;
import com.miaoshasha.common.exception.SystemException;
import com.miaoshasha.common.utils.MD5Util;
import com.miaoshasha.redis.annotation.EnableCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fengchaojun on 2018/5/9.
 */
@Slf4j
@Service
public class UserServiceImpl extends AbstractBaseService<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getLoginUser(String loginNo, String passowrd, Integer regChannel, Integer loginType) {

        User user = userMapper.getLoginUser(loginNo, regChannel, loginType);
        if (user == null) {
            throw new SystemException(ErrorCode.DATA_NULL_ERROR, "用户不存在");
        }

        String userPwd = user.getPassword();
        String pwdMd5 = MD5Util.MD5(passowrd);
        if (!userPwd.equals(pwdMd5)) {
            throw new SystemException(ErrorCode.PASSWORD_ERROR);
        }
        return user;
    }

    @Override
    public User getUserByLoginNo(String loginNo, Integer regChannel, LoginType loginType) {
        User user = userMapper.getLoginUser(loginNo, regChannel, loginType.getValue());
        if (user == null) {
            throw new SystemException(ErrorCode.DATA_NULL_ERROR, "用户不存在");
        }
        return user;
    }


    @Override
    public User getUserByPhoneNo(String phoneNo, Integer regChannel) {
        return getUserByLoginNo(phoneNo,regChannel,LoginType.PHONE);
    }

    @EnableCache(key = "#userId",expire = 50,concurrent = true)
    public User findById(Long userId) {
        return super.findById(userId);
    }

}
