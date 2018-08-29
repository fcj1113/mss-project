package com.miaoshasha.gateway.service.impl;

import com.miaoshasha.common.base.AbstractBaseService;
import com.miaoshasha.gateway.entity.ZuulRouteEntity;
import com.miaoshasha.gateway.locator.ZuulRouteResourceLocator;
import com.miaoshasha.gateway.mapper.ZuulRouteMapper;
import com.miaoshasha.gateway.service.ZuulRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 刷新配置服务,正常eurekaClient会更新路由列表（时间为心跳时间）。无需调用刷新。
 * Created by fengchaojun on 2018/7/18.
 */
@Service
public class ZuulRouteServiceImpl extends AbstractBaseService<ZuulRouteMapper,ZuulRouteEntity> implements ZuulRouteService {

    @Autowired
    @Qualifier("zuulRedisTemplate")
    private RedisTemplate<String, ZuulRouteEntity> zuulRedisTemplate;

    @Autowired
    private ZuulRouteMapper zuulRouteMapper ;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    @Qualifier("zuulRouteResourceLocator")
    private RouteLocator routeLocator;

    @Override
    public void refreshRoute() {
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }

    @Override
    public long save(ZuulRouteEntity zuulRouteEntity) {
       long res = zuulRouteMapper.insert(zuulRouteEntity);
        if (res > 0) {
            //成功后保存到redis中
            zuulRedisTemplate.opsForHash().put(ZuulRouteResourceLocator.ZUUL_ROUTE_REDIS_KEY, zuulRouteEntity.getPath(), zuulRouteEntity);
        }
        return zuulRouteEntity.getId();
    }
}
