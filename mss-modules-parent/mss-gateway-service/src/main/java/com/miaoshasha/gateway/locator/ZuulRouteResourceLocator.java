package com.miaoshasha.gateway.locator;

import com.miaoshasha.gateway.entity.ZuulRouteEntity;
import com.miaoshasha.gateway.service.ZuulRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 从持久化的资源中获取路由信息，redis+DB
 * Created by fengchaojun on 2018/7/18.
 */
@Slf4j
public class ZuulRouteResourceLocator extends AbstractZuulRouteLocator {

    public static final String ZUUL_ROUTE_REDIS_KEY = "zuul.route.redis.key";

    @Autowired
    private RedisTemplate<String, ZuulRouteEntity> redisTemplate;

    @Autowired
    private ZuulRouteService zuulRouteService ;

    public ZuulRouteResourceLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
    }

    /**
     * 从redis中加载
     *
     * @return
     */
    @Override
    public Map<String, ZuulRoute> loadRoute() {
        Map<String, ZuulRoute> routes = new LinkedHashMap<>();
        try {
            List<Object> redisResult = redisTemplate.opsForHash().values(ZUUL_ROUTE_REDIS_KEY);
            if (!CollectionUtils.isEmpty(redisResult)) {
                routes = getZuulRoute(redisResult);
            }else{//从数据库中获取
                List<ZuulRouteEntity> dbRes = zuulRouteService.findAll(new ZuulRouteEntity());
                if(!CollectionUtils.isEmpty(dbRes)){
                    Map<String, ZuulRouteEntity> dbRoutes = new HashMap<>();
                    for (ZuulRouteEntity zuulRouteEntity:dbRes){
                        dbRoutes.put(zuulRouteEntity.getPath(),zuulRouteEntity);
                    }
                    //再保存到redis中
                    redisTemplate.opsForHash().putAll(ZUUL_ROUTE_REDIS_KEY,dbRoutes);
                }
                routes = getZuulRoute(dbRes);

            }
        } catch (Exception e) {
            log.error("--------load zuul route from redis exception---------", e);
            e.printStackTrace();
        }
        return routes;
    }

    /**
     * 转换成ZuulRoute
     * @param redisResult
     * @return
     */
    public static Map<String, ZuulRoute> getZuulRoute(List<?> redisResult){
        Map<String, ZuulRoute> routes = new LinkedHashMap<>();
        for (Object item : redisResult) {
            ZuulRouteEntity routeEntity = (ZuulRouteEntity) item;

            if (StringUtils.isEmpty(routeEntity.getPath())
                    || !routeEntity.getEnabled()
                    || (StringUtils.isEmpty(routeEntity.getUrl()) && StringUtils.isEmpty(routeEntity.getServiceId()))) {
                continue;
            }

            ZuulRoute zuulRoute = new ZuulRoute();
            zuulRoute.setId(String.valueOf(routeEntity.getId()));
            if(!StringUtils.isEmpty(routeEntity.getCustomSensitiveHeaders())){
                zuulRoute.setCustomSensitiveHeaders(routeEntity.getCustomSensitiveHeaders());
            }
            if(!StringUtils.isEmpty(routeEntity.getPath())) {
                zuulRoute.setPath(routeEntity.getPath());
            }

            zuulRoute.setRetryable(routeEntity.getRetryable());
            zuulRoute.setStripPrefix(routeEntity.getStripPrefix());
            if(!StringUtils.isEmpty(routeEntity.getUrl())) {
                zuulRoute.setUrl(routeEntity.getUrl());
            }
            if(!StringUtils.isEmpty(routeEntity.getServiceId())) {
                zuulRoute.setServiceId(routeEntity.getServiceId());
            }
            Set<String> headers = new LinkedHashSet<>();
            if(!StringUtils.isEmpty(routeEntity.getSensitiveHeaders())){
                String[] strArr = routeEntity.getSensitiveHeaders().split(";");
                for(String header:strArr){
                    headers.add(header);
                }
            }
            zuulRoute.setSensitiveHeaders(headers);
            routes.put(zuulRoute.getPath(), zuulRoute);

            //log.info("---------zuul route："+zuulRoute.getPath()+"-----------");

        }
        return routes ;
    }

}
