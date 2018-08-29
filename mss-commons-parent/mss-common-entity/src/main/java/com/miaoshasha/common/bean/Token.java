package com.miaoshasha.common.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by fengchaojun on 2018/5/16.
 */
@Getter
@Setter
public class Token {

    /**
     * 用户id
     */
    private Long userId ;
    /**
     * token串
     */
    private String accessToken ;
    /**
     * 刷新token，失效时间比accessToken长
     */
    private String refreshToken;

    private String modulus;

    private String publicExponent;

    private String privateExponent;

    /**
     * 截止时间
     */
    private Long expiration ;

}
