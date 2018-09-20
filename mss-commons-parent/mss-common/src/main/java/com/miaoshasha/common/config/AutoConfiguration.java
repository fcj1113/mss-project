package com.miaoshasha.common.config;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
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
     * AlwaysSampler实例来指定sleuth 100%采样日志，建议线上减少。
     * 即spring.sleuth.sampler.percentage=1
     * @return
     */
    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }
}
