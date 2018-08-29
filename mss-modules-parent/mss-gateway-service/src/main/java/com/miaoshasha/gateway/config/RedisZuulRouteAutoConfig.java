package com.miaoshasha.gateway.config;

import com.miaoshasha.gateway.entity.ZuulRouteEntity;
import com.miaoshasha.gateway.locator.ZuulRouteResourceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by fengchaojun on 2018/7/18.
 */
@Configuration
public class RedisZuulRouteAutoConfig {

    @Autowired
    ZuulProperties zuulProperties;

    @Autowired
    ServerProperties server;

    @Bean("zuulRouteResourceLocator")
    @ConditionalOnMissingBean(ZuulRouteResourceLocator.class)
    public ZuulRouteResourceLocator zuulRouteResourceLocator() {
        return new ZuulRouteResourceLocator(this.server.getServletPrefix(), this.zuulProperties);
    }


    /**
     * 名称不要用redisTemplate，否则冲突
     * @param redisConnectionFactory
     * @return
     */
    @Bean("zuulRedisTemplate")
    public RedisTemplate<String, ZuulRouteEntity> zuulRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, ZuulRouteEntity> redisTmplate = new RedisTemplate<>();
        redisTmplate.setConnectionFactory(redisConnectionFactory);
        redisTmplate.setKeySerializer(new StringRedisSerializer());
        redisTmplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTmplate;
    }

}
