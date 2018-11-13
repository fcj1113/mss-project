package com.miaoshasha.gateway.support;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.Route;

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
public class RateLimitKeyGenerator {


    public String key(final HttpServletRequest request, final Route route) {
        final StringJoiner joiner = new StringJoiner(":");
        if (route != null) {
            joiner.add(route.getId());
        }

        return joiner.toString();
    }
}
