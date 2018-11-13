package com.miaoshasha.gateway.support;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.Route;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-11-13
 * Time：17:29
 * -----------------------------
 */
public enum RateLimitType {

    USER{
        @Override
        public String key(HttpServletRequest request, Route route, RateLimitUtils rateLimitUtils) {
            return rateLimitUtils.getUser(request);
        }
    },
    ROLE{
        @Override
        public String key(HttpServletRequest request, Route route, RateLimitUtils rateLimitUtils) {
            return rateLimitUtils.getUserRoles(request);
        }
    },
    IP{
        @Override
        public String key(HttpServletRequest request, Route route, RateLimitUtils rateLimitUtils) {
            return rateLimitUtils.getRemoteAddress(request);
        }
    },
    URL{
        @Override
        public String key(HttpServletRequest request, Route route, RateLimitUtils rateLimitUtils) {
            return Optional.ofNullable(route).map(Route::getPath).orElse(StringUtils.EMPTY);
        }
    };

    public abstract String key(HttpServletRequest request, Route route, RateLimitUtils rateLimitUtils);
}
