package com.miaoshasha.gateway.service;

import com.miaoshasha.common.base.BaseService;
import com.miaoshasha.gateway.entity.ZuulRouteEntity;

/**
 * Created by fengchaojun on 2018/7/24.
 */
public interface ZuulRouteService extends BaseService<ZuulRouteEntity> {

    /**
     * 刷新
     */
    void refreshRoute();
}
