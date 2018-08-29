package com.miaoshasha.logcenter.handler.oplog;

import com.alibaba.fastjson.JSON;
import com.miaoshasha.common.bean.OpLog;
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
public class RedisLogHandler implements LogHandler{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String KEY = "mss-logcenter-service:op_log";

    @Override
    public void save(OpLog opLog) {
        stringRedisTemplate.opsForValue().set(KEY+":"+ opLog.getLogId(), JSON.toJSONString(opLog));
    }
}
