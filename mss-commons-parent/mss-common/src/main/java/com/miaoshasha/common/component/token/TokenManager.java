package com.miaoshasha.common.component.token;

/**
 * Created by fengchaojun on 2018/5/16.
 */
public interface TokenManager {

    /**
     * 创建一个token
     * @param userId 指定用户的id
     * @return 生成的token
     */
    String createToken(long userId);

    /**
     * 校验token是否有效
     * @param token token
     * @return 若两个TOKEN都没有失效，正常返回，
     *  若accessToken失效，refreshToken没有失效，则生成新的TOKEN，
     *  若都失效，返回null。
     */
     String verifyToken(String token);

    /**
     * 根据token获取用户id
     * @param token
     * @return
     */
     Long findUserIdByToken(String token);

    /**
     * 清除token
     * @param tokenStr 登录用户的id
     */
    void deleteToken(String tokenStr);

}
