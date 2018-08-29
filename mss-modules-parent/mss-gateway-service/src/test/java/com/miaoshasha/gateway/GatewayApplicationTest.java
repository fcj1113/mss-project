package com.miaoshasha.gateway;

import com.miaoshasha.gateway.entity.ZuulRouteEntity;
import com.miaoshasha.gateway.service.ZuulRouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fengchaojun on 2018/7/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayApplicationTest {

    @Autowired
    private ZuulRouteService routeService ;

    @Test
    public void saveRouteRedis(){
        ZuulRouteEntity zuulRouteEntity = new ZuulRouteEntity();
        zuulRouteEntity.setEnabled(true);
        zuulRouteEntity.setPath("/mss-auth-service/**");
        zuulRouteEntity.setRouteName("/mss-auth-service");
        zuulRouteEntity.setServiceId("/mss-auth-service");
        zuulRouteEntity.setStripPrefix(false);
        routeService.save(zuulRouteEntity);
    }

    @Test
    public void refreshRoute(){
        routeService.refreshRoute();
    }
}
