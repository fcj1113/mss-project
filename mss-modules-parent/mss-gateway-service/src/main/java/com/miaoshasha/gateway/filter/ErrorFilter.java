package com.miaoshasha.gateway.filter;

import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.exception.SystemException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_ERROR_FILTER_ORDER;

/**
 * 统一异常处理
 */
@Slf4j
@Component
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        //需要在默认的 SendErrorFilter 之前
        return SEND_ERROR_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getThrowable() != null;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();

        ctx.remove("throwable");
        ctx.setSendZuulResponse(false);
        ctx.addZuulResponseHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

        if (throwable.getCause() != null) {
            if (throwable.getCause() instanceof SystemException) {
                SystemException systemException = (SystemException) throwable.getCause();
                ctx.setResponseBody(DataResult.faild(systemException.getRetCode(), systemException.getRetMsg()).toJson());
            } else {
                ctx.setResponseBody(DataResult.faild(ErrorCode.SYSTEM_ERROR).toJson());
            }
        } else {
            ctx.setResponseBody(DataResult.faild(ErrorCode.SYSTEM_ERROR).toJson());
        }

        return null;
    }
}
