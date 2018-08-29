package com.miaoshasha.seckill.controller;

import com.miaoshasha.common.annotation.OpLog;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.dto.order.PromoDTO;
import com.miaoshasha.common.entity.store.PromoInfo;
import com.miaoshasha.common.enums.OpType;
import com.miaoshasha.seckill.mq.OrderMsgPublisher;
import com.miaoshasha.seckill.service.PromoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fengchaojun on 2018/6/14.
 */

@Api(tags = "PromoController", description = "")
@RestController
@RequestMapping(value = "promo")
public class PromoController extends BaseController {

    @Autowired
    private PromoService promoService ;

    @Autowired
    @Qualifier("orderMsgPublisher")
    private OrderMsgPublisher orderMsgPublisher;

    @ApiOperation(value = "抢购api", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "promoDTO", value = "活动信息", paramType = "body", required = true, dataType = "PromoDTO")
    })
    @RequestMapping(value = "purchase",method = RequestMethod.POST)
    public DataResult<Boolean> purchase(@RequestBody PromoDTO promoDTO){
        //promoService.purchase(promoDTO);
        return DataResult.success(false);
    }

    @ApiOperation(value = "保存活动", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "promoInfo", value = "活动信息", paramType = "body", required = true, dataType = "PromoInfo"),
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "path", required = true, dataType = "Long")
    })
    @RequestMapping(value = "save/{userId}",method = RequestMethod.POST)
    public DataResult<Boolean> save(@RequestBody PromoInfo promoInfo,@PathVariable("id") Long userId){
        promoService.save(promoInfo);
        return DataResult.success(false);
    }

    @ApiOperation(value = "获取单个活动详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "活动ID", paramType = "path", required = true, dataType = "Long")
    })
    @OpLog(userId = "1",notes = "获取单个活动详情",opType = OpType.QUERY)
    @RequestMapping(value = "findById/{id}",method = RequestMethod.POST)
    public DataResult<PromoInfo> findById(@PathVariable("id") Long id){
        return DataResult.success(promoService.findById(id));
    }
}
