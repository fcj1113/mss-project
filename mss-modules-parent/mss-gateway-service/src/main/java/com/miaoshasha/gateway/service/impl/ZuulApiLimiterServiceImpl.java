package com.miaoshasha.gateway.service.impl;

import com.miaoshasha.common.base.AbstractBaseService;
import com.miaoshasha.gateway.entity.ZuulRateLimiterEntity;
import com.miaoshasha.gateway.mapper.ZuulApiLimiterMapper;
import com.miaoshasha.gateway.service.ZuulApiLimiterService;
import org.springframework.stereotype.Service;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-13
 * Time：20:29
 * -----------------------------
 */

@Service
public class ZuulApiLimiterServiceImpl extends AbstractBaseService<ZuulApiLimiterMapper,ZuulRateLimiterEntity> implements ZuulApiLimiterService {
}
