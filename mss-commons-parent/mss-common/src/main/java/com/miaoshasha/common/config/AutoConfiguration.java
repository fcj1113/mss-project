package com.miaoshasha.common.config;

import com.miaoshasha.common.utils.RedisCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 通用的自动配置
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-27
 * Time：20:09
 * ================================
 */
@Configuration
public class AutoConfiguration {

    /**
     * 实例化redis工具
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisCache redisCache(StringRedisTemplate redisTemplate){
        return new RedisCache(redisTemplate);
    }


    /**
     * AlwaysSampler实例来指定sleuth 100%输出日志
     * 即spring.sleuth.sampler.percentage=1
     * @return
     */
    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }
}
