package com.miaoshasha.auth.service.impl;

import com.miaoshasha.api.base.UserRemoteClient;
import com.miaoshasha.auth.service.AuthService;
import com.miaoshasha.common.component.token.RedisTokenManager;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.dto.user.UserDTO;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.exception.SystemException;
import com.miaoshasha.common.utils.AssertUtil;
import com.miaoshasha.common.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by fengchaojun on 2018/8/1.
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRemoteClient userRemoteClient;

    @Autowired
    private RedisTokenManager redisTokenManager ;

    @Override
    public UserDTO login(String phoneNo, String password, Integer channel) {
        DataResult<UserDTO> userDTORes = userRemoteClient.getUserByPhoneNo(phoneNo, channel);
        AssertUtil.assertResult(userDTORes);
        UserDTO userDTO = userDTORes.getResult();
        String userPwd = userDTO.getPassword();
        String pwdMd5 = MD5Util.MD5(password);
        if (!userPwd.equals(pwdMd5)) {//密码错误
            throw new SystemException(ErrorCode.PASSWORD_ERROR);
        }
        //生成token
        userDTO.setToken(redisTokenManager.createToken(userDTO.getUserId()));

        return userDTO;
    }

    @Override
    public UserDTO getUserByToken(String tokenStr) {
        //从缓存中获取
        DataResult<UserDTO> userRes = userRemoteClient.getInfoById(redisTokenManager.findUserIdByToken(tokenStr));
        AssertUtil.assertResult(userRes);
        return userRes.getResult();
    }

    @Override
    public boolean verifyToken(String token) {
       return StringUtils.isEmpty(redisTokenManager.verifyToken(token))?false:true;
    }
}
