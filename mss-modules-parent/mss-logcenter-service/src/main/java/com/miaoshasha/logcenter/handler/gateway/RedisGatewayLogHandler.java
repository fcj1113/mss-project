package com.miaoshasha.logcenter.handler.gateway;

import com.alibaba.fastjson.JSON;
import com.miaoshasha.common.entity.system.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-24
 * Time：15:13
 * ================================
 */

@Component
public class RedisGatewayLogHandler implements GatewayLogHandler{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String KEY = "mss-logcenter-service:gateway_log";

    @Override
    public void save(SysLog log) {
        stringRedisTemplate.opsForValue().set(KEY+":"+log.getLogId(), JSON.toJSONString(log));
    }
}
