package com.miaoshasha.base.service.user;

import com.miaoshasha.common.base.BaseService;
import com.miaoshasha.common.entity.user.User;
import com.miaoshasha.common.enums.LoginType;

/**
 * Created by fengchaojun on 2018/5/9.
 */
public interface UserService extends BaseService<User> {


    /**
     *  根据手机号码查询登录用户信息
     * @param loginNo 登录账号
     * @param passowrd 密码
     * @param regChannel 渠道
     * @param loginType 登录方式
     * @return
     */
    User getLoginUser(String loginNo, String passowrd, Integer regChannel, Integer loginType);


    /**
     * 根据登录号码获取用户信息
     * @param loginNo
     * @param regChannel
     * @param loginType
     * @return
     */
    User getUserByLoginNo(String loginNo, Integer regChannel, LoginType loginType);


    /**
     * 根据手机号码获取用户信息
     * @param phoneNo
     * @param regChannel
     * @return
     */
    User getUserByPhoneNo(String phoneNo, Integer regChannel);
}
