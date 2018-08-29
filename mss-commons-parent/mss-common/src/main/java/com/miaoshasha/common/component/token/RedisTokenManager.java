package com.miaoshasha.common.component.token;

import com.alibaba.fastjson.JSON;
import com.miaoshasha.common.bean.Token;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.exception.SystemException;
import com.miaoshasha.common.utils.MD5Util;
import com.miaoshasha.common.utils.RedisCache;
import com.miaoshasha.common.utils.TokenUtil;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by fengchaojun on 2018/5/16.
 */
@Component
@Slf4j
public class RedisTokenManager implements TokenManager {

    public static final String KEY_PREFIX = "MSS_TOKEN:";

    @Autowired
    private RedisCache redisCache;


    @Override
    public String createToken(long userId) {
        //生成TOKEN
        Token token = TokenUtil.getInstance().generateToken(userId);
        String tokenStr = token.getAccessToken() + "__" + token.getRefreshToken();
        String md5 = MD5Util.MD5(tokenStr);
        //存储到redis中
        redisCache.set(KEY_PREFIX + md5, token);
        return tokenStr;
    }

    @Override
    public String verifyToken(String tokenStr) {
        if (StringUtils.isEmpty(tokenStr)) {
            throw new SystemException(ErrorCode.ILLEGAL_CALL);
        }
        String md5 = MD5Util.MD5(tokenStr);

        String[] tokenArr = tokenStr.split("__");
        if (tokenArr.length != 2) {//若不是两个则是非法的
            throw new SystemException(ErrorCode.ILLEGAL_CALL);
        }

        Token token = JSON.parseObject(redisCache.get(KEY_PREFIX + md5), Token.class);
        //若token不符，则非法
        if (token == null || !tokenStr.equals(token.getAccessToken() + "__" + token.getRefreshToken())) {
            throw new SystemException(ErrorCode.ILLEGAL_CALL);
        }

        //进行校验
        DataResult<Boolean> checkAcc = TokenUtil.getInstance().checkAccessToken(token);
        DataResult<Boolean> checkRef = TokenUtil.getInstance().checkRefreshToken(token);
        if (checkAcc.getStatus().getRetCode() == 0 && checkRef.getStatus().getRetCode() == 0) {
            return tokenStr;//校验成功，返回原有token
        } else if (checkAcc.getStatus().getRetCode() == ErrorCode.VERIFY_EXPIRE.getCode() && checkRef.getStatus().getRetCode() == 0) {
            //accessToken超时失效，refreshToken未超时失效，生成新的token返回
            deleteToken(tokenStr);
            token = TokenUtil.getInstance().generateToken(token.getUserId());
            tokenStr = token.getAccessToken() + "__" + token.getRefreshToken();
            md5 = MD5Util.MD5(tokenStr);
            redisCache.set(KEY_PREFIX + md5, token);
            return tokenStr;
        } else if (checkAcc.getStatus().getRetCode() == ErrorCode.VERIFY_EXPIRE.getCode() && checkRef.getStatus().getRetCode() == ErrorCode.VERIFY_EXPIRE.getCode()) {
            //返回签名过期错误，要重新登录
            throw new SystemException(ErrorCode.VERIFY_EXPIRE);
        } else {
            //非法请求
            throw new SystemException(ErrorCode.ILLEGAL_CALL);
        }

    }

    @Override
    public Long findUserIdByToken(String tokenStr) {
        String md5 = MD5Util.MD5(tokenStr);
        Token token = JSON.parseObject(redisCache.get(KEY_PREFIX + md5), Token.class);
        if(token == null){
            throw new SystemException(ErrorCode.DATA_NULL_ERROR);
        }
        return token.getUserId();
    }


    @Override
    public void deleteToken(String tokenStr) {
        String md5 = MD5Util.MD5(tokenStr);
        redisCache.delete(KEY_PREFIX + md5);
    }
}
