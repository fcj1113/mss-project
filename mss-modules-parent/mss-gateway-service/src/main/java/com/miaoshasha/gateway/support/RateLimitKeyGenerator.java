package com.miaoshasha.gateway.support;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.StringJoiner;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-11-13
 * Time：16:17
 * -----------------------------
 */
@Component
public class RateLimitKeyGenerator {

    @Autowired
    private RateLimitUtils rateLimitUtils;
    /**
     * 前缀
     */
    public static final String KEY_PREFIX="ZUULRATELIMITER";

    public String key(HttpServletRequest request, Route route,RateLimitType rateLimitType) {
        final StringJoiner joiner = new StringJoiner(":");
        joiner.add(KEY_PREFIX);
        if (route != null) {
            joiner.add(route.getId());
        }

        if(rateLimitType == null){
            rateLimitType = RateLimitType.URL;
        }
        joiner.add(rateLimitType.key(request,route,rateLimitUtils));
        return joiner.toString();
    }
}
