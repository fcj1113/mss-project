package com.miaoshasha.gateway.entity;

import com.google.common.util.concurrent.RateLimiter;
import com.miaoshasha.common.base.BaseEntity;
import com.miaoshasha.common.enums.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-09-12
 * Time：16:40
 * ================================
 *
 */

@Data
public class ZuulRateLimiterEntity extends BaseLimiterEntity implements BaseEntity {


    private Long id;

    private Long routeId;

    private String serviceId;

    private String path ;


}
