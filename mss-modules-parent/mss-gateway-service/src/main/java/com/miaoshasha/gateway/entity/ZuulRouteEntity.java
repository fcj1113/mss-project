package com.miaoshasha.gateway.entity;

import com.google.common.util.concurrent.RateLimiter;
import com.miaoshasha.common.base.BaseEntity;
import com.miaoshasha.common.enums.ErrorCode;
import lombok.Data;

/**
 * Created by fengchaojun on 2018/7/18.
 */
@Data
public class ZuulRouteEntity extends BaseLimiterEntity implements BaseEntity{
    private static final long serialVersionUID = -7158043313856484077L;

    /**
     * The ID of the route (the same as its map key by default).
     */
    private Long id;

    /**
     * The path (pattern) for the route, e.g. /foo/**.
     */
    private String path;

    /**
     * The service ID (if any) to map to this route. You can specify a physical URL or
     * a service, but not both.
     */
    private String serviceId;

    /**
     * A full physical URL to map to the route. An alternative is to use a service ID
     * and service discovery to find the physical address.
     */
    private String url;

    /**
     * Flag to determine whether the prefix for this route (the path, minus pattern
     * patcher) should be stripped before forwarding.
     */
    private Boolean stripPrefix = true;

    /**
     * Flag to indicate that this route should be retryable (if supported). Generally
     * retry requires a service ID and ribbon.
     */
    private Boolean retryable;

    /**
     * List of sensitive headers that are not passed to downstream requests. Defaults
     * to a "safe" set of headers that commonly contain base credentials. It's OK to
     * remove those from the list if the downstream service is part of the same system
     * as the proxy, so they are sharing authentication data. If using a physical URL
     * outside your own domain, then generally it would be a bad idea to leak base
     * credentials.
     */
    private String sensitiveHeaders ;

    private Boolean customSensitiveHeaders ;

    /**
     * 是否可用
     */
    private Boolean enabled ;

    /**
     * 路由名称
     */
    private String routeName ;



}
