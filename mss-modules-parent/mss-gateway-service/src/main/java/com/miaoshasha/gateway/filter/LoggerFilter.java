package com.miaoshasha.gateway.filter;

import com.miaoshasha.gateway.service.LoggerService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * Created by fengchaojun on 2018/7/12.
 */
@Slf4j
@Component
public class LoggerFilter extends ZuulFilter {

    @Autowired
    private LoggerService loggerService;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        log.info("-------LoggerFilter::::::::shouldFilter");
        return true;
    }

    @Override
    public Object run() {
        loggerService.send(RequestContext.getCurrentContext());
        return null;
    }
}
