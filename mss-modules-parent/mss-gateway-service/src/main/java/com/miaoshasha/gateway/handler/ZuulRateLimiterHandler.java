package com.miaoshasha.gateway.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.util.concurrent.RateLimiter;
import com.miaoshasha.common.exception.SystemException;
import com.miaoshasha.gateway.entity.BaseLimiterEntity;
import com.miaoshasha.gateway.entity.ZuulRateLimiterEntity;
import com.miaoshasha.gateway.entity.ZuulRouteEntity;
import com.miaoshasha.gateway.service.ZuulApiLimiterService;
import com.miaoshasha.gateway.service.ZuulRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Component;

import javax.xml.ws.soap.Addressing;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 限流的核心处理器
 * 每隔1小时从缓存中拉取限流的配置数据
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
    private ZuulRouteService zuulRouteService ;

    @Autowired
    private ZuulApiLimiterService zuulApiLimiterService;

    /**
     * id作为key
     */
    private Map<Long,ZuulRouteEntity> zuulRouteEntityMap = new ConcurrentHashMap<>();

    /**
     * id+path作为key
     */
    private Map<String,ZuulRateLimiterEntity> zuulRateLimiterEntityMap = new ConcurrentHashMap<>();


    public void tryAcquire(Route route){
        //从预发环境中加载，不在限流中，直接返回
        BaseLimiterEntity baseLimiterEntity = loadLimiterEntity(route);
        if(baseLimiterEntity == null || !baseLimiterEntity.isLimiterEnabled()){
            return ;
        }
        RateLimiter rateLimiter = baseLimiterEntity.rateLimiter();
        if(rateLimiter.tryAcquire(baseLimiterEntity.getPermits(),baseLimiterEntity.getTimeout(),TimeUnit.valueOf(baseLimiterEntity.getTimeUnit()))){
            return ;
        }
        throw new SystemException(baseLimiterEntity.getErrorCode(),baseLimiterEntity.getErrorMsg());
    }


    public BaseLimiterEntity loadLimiterEntity(Route route){
        BaseLimiterEntity baseLimiterEntity = null;

        if(CollectionUtil.isEmpty(zuulRouteEntityMap)){
            loadRouteEntity();
        }

        if(CollectionUtil.isEmpty(zuulRateLimiterEntityMap)){
            loadRateLimiterEntity();
        }

        //map不为空时
        if(!CollectionUtil.isEmpty(zuulRateLimiterEntityMap)){
            baseLimiterEntity = new BaseLimiterEntity();
            ZuulRateLimiterEntity zuulRateLimiterEntity = zuulRateLimiterEntityMap.get(route.getId()+route.getPath());
            if(zuulRateLimiterEntity != null)
                BeanUtils.copyProperties(zuulRateLimiterEntity,baseLimiterEntity);
        }

        //map不为空时
        if(!CollectionUtil.isEmpty(zuulRouteEntityMap)){
            if(baseLimiterEntity == null){
                baseLimiterEntity = new BaseLimiterEntity();
            }
            ZuulRouteEntity zuulRouteEntity = zuulRouteEntityMap.get(route.getId());

            if(zuulRouteEntity != null)
                BeanUtils.copyProperties(zuulRouteEntity,baseLimiterEntity);
        }

        return baseLimiterEntity;
    }



    /**
     * 从缓存中加载api限流信息
     */
    public void loadRateLimiterEntity(){
        zuulRateLimiterEntityMap.clear();
        List<ZuulRateLimiterEntity> list = zuulApiLimiterService.findAll(new ZuulRateLimiterEntity());
        if(!CollectionUtil.isEmpty(list)){
            for (ZuulRateLimiterEntity zuulRateLimiterEntity:list){
                zuulRateLimiterEntityMap.put(zuulRateLimiterEntity.getRouteId()+zuulRateLimiterEntity.getPath(),zuulRateLimiterEntity);
            }
        }
    }

    /**
     * 从缓存中加载服务限流信息
     */
    public void loadRouteEntity(){
        zuulRouteEntityMap.clear();
        List<ZuulRouteEntity> list = zuulRouteService.findAll(new ZuulRouteEntity());
        if(!CollectionUtil.isEmpty(list)){
            log.info("---------ZuulRouteEntity_List="+list.size());
            for (ZuulRouteEntity zuulRouteEntity:list){
                zuulRouteEntityMap.put(zuulRouteEntity.getId(),zuulRouteEntity);
            }
        }
    }
}
