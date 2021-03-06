package com.miaoshasha.gateway.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.util.concurrent.RateLimiter;
import com.miaoshasha.common.exception.SystemException;
import com.miaoshasha.gateway.entity.BaseLimiterEntity;
import com.miaoshasha.gateway.entity.ZuulRateLimiterEntity;
import com.miaoshasha.gateway.entity.ZuulRouteEntity;
import com.miaoshasha.gateway.service.ZuulApiLimiterService;
import com.miaoshasha.gateway.service.ZuulRouteService;
import com.miaoshasha.gateway.support.RateLimitKeyGenerator;
import com.miaoshasha.gateway.support.RateLimitType;
import com.miaoshasha.redis.util.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.soap.Addressing;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 限流的核心处理器
 * 每隔1小时从缓存中拉取限流的配置数据
 *
 * @author fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-09-12
 * Time：18:44
 * ================================
 */
@Slf4j
@Component
public class ZuulRateLimiterHandler {

    @Autowired
    private ZuulRouteService zuulRouteService;

    @Autowired
    private ZuulApiLimiterService zuulApiLimiterService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RateLimitKeyGenerator rateLimitKeyGenerator;
    /**
     * id作为key
     */
    private Map<Long, ZuulRouteEntity> zuulRouteEntityMap = new ConcurrentHashMap<>();

    /**
     * id+path作为key
     */
    private Map<String, ZuulRateLimiterEntity> zuulRateLimiterEntityMap = new ConcurrentHashMap<>();


    public void tryAcquire(Route route, HttpServletRequest request) {
        //从预发环境中加载，不在限流中，直接返回
        BaseLimiterEntity baseLimiterEntity = loadLimiterEntity(route);
        if (baseLimiterEntity == null || !baseLimiterEntity.isLimiterEnabled()) {
            return;
        }
        boolean distributedEnabled = baseLimiterEntity.isDistributedEnabled();
        if (distributedEnabled) {//启用分布式限流，
            /*
             * 1.生成redis的key，规则：serviceId+限流方式下的key（url，user，role，ip等等），默认是url
             *
             * 2.每次请求符合key的规则进行redis原子递增，把请求总数放在redis中（requestTotal）
             *
             * 3.第一次递增设置key的超时时间
             *
             * 4.返回Math.max(-1,limit-requestTotal)
             * */

            RateLimitType rateLimitType = baseLimiterEntity.getRateLimitType();
            String key = rateLimitKeyGenerator.key(request, route, rateLimitType);
            Long requestTotal = redisCache.increment(key, 1L);
            //第一次进入后，会进行设置超时时间，若key超时，requestTotal从1开始进行计算
            if (requestTotal != null && requestTotal.equals(1L)) {
                redisCache.expire(key, baseLimiterEntity.getDistributedTime(), TimeUnit.SECONDS);
            }

            //计算剩余数量，若小于等于-1，抛出请求过多的异常
            Long remaining = Math.max(-1, baseLimiterEntity.getDistributedPermits() - requestTotal);

            if (remaining > -1) {
                return;
            }

        } else {
            //单实例限流
            RateLimiter rateLimiter = baseLimiterEntity.rateLimiter();
            if (rateLimiter.tryAcquire(baseLimiterEntity.getPermits(), baseLimiterEntity.getTimeout(), TimeUnit.valueOf(baseLimiterEntity.getTimeUnit()))) {
                return;
            }
        }
        throw new SystemException(baseLimiterEntity.getErrorCode(), baseLimiterEntity.getErrorMsg());
    }


    public BaseLimiterEntity loadLimiterEntity(Route route) {
        BaseLimiterEntity baseLimiterEntity = null;

        if (CollectionUtil.isEmpty(zuulRouteEntityMap)) {
            loadRouteEntity();
        }

        if (CollectionUtil.isEmpty(zuulRateLimiterEntityMap)) {
            loadRateLimiterEntity();
        }

        //map不为空时
        if (!CollectionUtil.isEmpty(zuulRateLimiterEntityMap)) {
            baseLimiterEntity = new BaseLimiterEntity();
            ZuulRateLimiterEntity zuulRateLimiterEntity = zuulRateLimiterEntityMap.get(route.getId() + route.getPath());
            if (zuulRateLimiterEntity != null)
                BeanUtils.copyProperties(zuulRateLimiterEntity, baseLimiterEntity);
        }

        //map不为空时
        if (!CollectionUtil.isEmpty(zuulRouteEntityMap)) {
            if (baseLimiterEntity == null) {
                baseLimiterEntity = new BaseLimiterEntity();
            }
            ZuulRouteEntity zuulRouteEntity = zuulRouteEntityMap.get(route.getId());

            if (zuulRouteEntity != null)
                BeanUtils.copyProperties(zuulRouteEntity, baseLimiterEntity);
        }

        return baseLimiterEntity;
    }


    /**
     * 从缓存中加载api限流信息
     */
    public void loadRateLimiterEntity() {
        zuulRateLimiterEntityMap.clear();
        List<ZuulRateLimiterEntity> list = zuulApiLimiterService.findAll(new ZuulRateLimiterEntity());
        if (!CollectionUtil.isEmpty(list)) {
            for (ZuulRateLimiterEntity zuulRateLimiterEntity : list) {
                zuulRateLimiterEntityMap.put(zuulRateLimiterEntity.getRouteId() + zuulRateLimiterEntity.getPath(), zuulRateLimiterEntity);
            }
        }
    }

    /**
     * 从缓存中加载服务限流信息
     */
    public void loadRouteEntity() {
        zuulRouteEntityMap.clear();
        List<ZuulRouteEntity> list = zuulRouteService.findAll(new ZuulRouteEntity());
        if (!CollectionUtil.isEmpty(list)) {
            log.info("---------ZuulRouteEntity_List=" + list.size());
            for (ZuulRouteEntity zuulRouteEntity : list) {
                zuulRouteEntityMap.put(zuulRouteEntity.getId(), zuulRouteEntity);
            }
        }
    }
}
