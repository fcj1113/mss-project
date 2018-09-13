package com.miaoshasha.gateway.filter;

import com.miaoshasha.gateway.handler.ZuulRateLimiterHandler;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关限流过滤器
 *
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-09-10
 * Time：22:05
 * ================================
 */
@Slf4j
@Component
public class ZuulRateLimiterFilter extends ZuulFilter {

    /**
     * 限流总开关，配置文件中配置
     */
    @Value("${zuul.ratelimiter.enabled:false}")
    private boolean limiterEnabled = false;

    @Autowired
    @Qualifier("zuulRouteResourceLocator")
    private RouteLocator routeLocator;

    @Autowired
    private ZuulRateLimiterHandler zuulRateLimiterHandler ;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        //是否启用
        return limiterEnabled;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();

        Route route = routeLocator.getMatchingRoute(uri);
        zuulRateLimiterHandler.tryAcquire(route);
        return null;
    }
}
