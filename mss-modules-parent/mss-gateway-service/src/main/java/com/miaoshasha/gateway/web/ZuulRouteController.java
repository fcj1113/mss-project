package com.miaoshasha.gateway.web;

import cn.hutool.core.lang.Assert;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.gateway.entity.ZuulRouteEntity;
import com.miaoshasha.gateway.service.ZuulRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengchaojun on 2018/7/18.
 */
@RestController
@RequestMapping("zuulRoute")
public class ZuulRouteController {

    @Autowired
    private ZuulRouteService zuulRouteService;

    /**
     * 保存到数据库和缓存中
     * @param zuulRouteEntity
     * @return
     */
    @RequestMapping("save")
    public DataResult<Long> save(@RequestBody ZuulRouteEntity zuulRouteEntity) {
        Assert.isNull(zuulRouteEntity);
        //先保存到数据库中
        long id = zuulRouteService.save(zuulRouteEntity);
        return DataResult.success(id);
    }


}
