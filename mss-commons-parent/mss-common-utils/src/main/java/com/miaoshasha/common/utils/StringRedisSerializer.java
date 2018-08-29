package com.miaoshasha.common.utils;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * 重写StringRedisSerializer，实现RedisSerializer接口，防止类型转换报错
 * Created by fengchaojun on 2018/6/28.
 */
public class StringRedisSerializer implements RedisSerializer<Object> {

    private final Charset charset;

    public StringRedisSerializer() {
        this(Charset.forName("UTF8"));
    }

    public StringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    public String deserialize(byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset));
    }

    @Override
    public byte[] serialize(Object object) {
        if(object ==null ){
            return null ;
        }
        String string = String.valueOf(object);
        return  string.getBytes(charset);
    }


}
