package com.miaoshasha.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.RSA;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.bean.Token;
import com.miaoshasha.common.enums.ErrorCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * 根据Rsa生成token
 *
 * Created by fengchaojun on 2018/5/16.
 */
@Slf4j
public class TokenUtil {

    private static volatile TokenUtil tokenUtil = null;

    private TokenUtil(){}

    public static TokenUtil getInstance(){
        if(tokenUtil == null){
            synchronized(TokenUtil.class){
                if(tokenUtil == null){
                    tokenUtil = new TokenUtil();
                }
            }
        }

        return tokenUtil;
    }


    /**
     * 生成token
     * @param userId
     * @param expTime token的失效时间，单位分钟。
     * @return
     */
    public Token generateToken(Long userId,int expTime){
        Token token = new Token();

        RSA rsa = new RSA();
        RSAPrivateKey privateKey = (RSAPrivateKey) rsa.getPrivateKey();
        RSAPublicKey publicKey = (RSAPublicKey) rsa.getPublicKey();
        //设置超时时间
        long endTime = System.currentTimeMillis() + 1000 * 60 * expTime;
        token.setAccessToken(Jwts.builder().setSubject(String.valueOf(userId)).setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.RS512, privateKey).compact());

        /**
         * 比AccessToken长一个小时
         */
        long refreshEndTime = System.currentTimeMillis() + 1000 * 60 * (expTime+60);
        token.setRefreshToken(Jwts.builder().setSubject(String.valueOf(userId)).setExpiration(new Date(refreshEndTime))
                .signWith(SignatureAlgorithm.RS512, privateKey).compact());

        token.setModulus(privateKey.getModulus().toString());
        token.setPrivateExponent(privateKey.getPrivateExponent().toString());
        token.setPublicExponent(publicKey.getPublicExponent().toString());
        token.setUserId(userId);
        return token;
    }



    /**
     *  生成token,默认token的时间为24小时
     * @param userId
     * @return
     */
    public Token generateToken(Long userId){
        return generateToken(userId,1440);
    }


    /**
     * 检查AccessToken是否合法
     * @param token token对象
     * @return DataResult
     */
    public DataResult<Boolean> checkAccessToken(Token token) {
        int res = checkToken(token.getAccessToken(),token.getModulus(),token.getPublicExponent());
        if(res == 0){
            return DataResult.success(true);
        } else if (res == ErrorCode.VERIFY_EXPIRE.getCode()) {
            return DataResult.faild(ErrorCode.VERIFY_EXPIRE);
        } else {
            return DataResult.faild(ErrorCode.ILLEGAL_CALL);
        }
    }

    /**
     * 检查RefreshToken是否合法
     * @param token token对象
     * @return DataResult
     */
    public DataResult<Boolean> checkRefreshToken(Token token) {
        int res = checkToken(token.getRefreshToken(),token.getModulus(),token.getPublicExponent());
        if(res == 0){
            return DataResult.success(true);
        } else if (res == ErrorCode.VERIFY_EXPIRE.getCode()) {
            return DataResult.faild(ErrorCode.VERIFY_EXPIRE);
        } else {
            return DataResult.faild(ErrorCode.ILLEGAL_CALL);
        }
    }


    /**
     * 利用公钥解密，并判断token是否有效
     * @param token
     * @param modulus
     * @param publicExponent
     * @return
     */
    public int checkToken(String token ,String modulus,String publicExponent){
        try {
            RSAPublicKey publicKey = (RSAPublicKey) RSA.generatePublicKey(new BigInteger(modulus),new BigInteger(publicExponent));;
            Claims claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();

            String sub = claims.get("sub", String.class);
            log.debug("-------------userId=sub="+sub+"------------");
            return 0;
        } catch (ExpiredJwtException e) {
            // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
            return ErrorCode.VERIFY_EXPIRE.getCode();
        } catch (SignatureException e) {
            // 在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
            return ErrorCode.ILLEGAL_CALL.getCode();
        } catch (Exception e) {
            return ErrorCode.ILLEGAL_CALL.getCode();
        }
    }

    public static void main(String[] args) {
        Token token = TokenUtil.getInstance().generateToken(1l);
        String str = Base64.encode(token.getAccessToken()+"__"+token.getRefreshToken());
        System.out.println(str);
        System.out.println(new String(Base64.decode(str,"UTF-8")));
        System.out.println(MD5Util.MD5(str));

    }
}
