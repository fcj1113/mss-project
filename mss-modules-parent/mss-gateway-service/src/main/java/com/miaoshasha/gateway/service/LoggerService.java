package com.miaoshasha.gateway.service;

import com.netflix.zuul.context.RequestContext;

/**
 * Created by fengchaojun on 2018/7/27.
 */
public interface LoggerService {

    /**
     * 发送消息
     * @param requestContext
     */
    void send(RequestContext requestContext);
}
