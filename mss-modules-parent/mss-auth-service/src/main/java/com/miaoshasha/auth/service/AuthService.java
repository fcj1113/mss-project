package com.miaoshasha.auth.service;

import com.miaoshasha.common.dto.user.UserDTO;

/**
 * Created by fengchaojun on 2018/8/1.
 */
public interface AuthService {

    /**
     * 登录授权操作
     * @param phoneNo
     * @param password
     * @param channel
     * @return
     */
    UserDTO login(String phoneNo,String password,Integer channel);

    /**
     * 根据token获取登录信息
     * @param token
     * @return
     */
    UserDTO getUserByToken(String token);

    /**
     * 验证token有效性
     * @param token
     * @return
     */
    boolean verifyToken(String token);
}
