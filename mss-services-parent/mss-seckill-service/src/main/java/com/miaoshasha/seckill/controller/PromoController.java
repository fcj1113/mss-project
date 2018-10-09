package com.miaoshasha.seckill.controller;

import cn.hutool.core.date.DateUtil;
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

import java.util.Date;

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
    public DataResult<Boolean> save(@RequestBody PromoInfo promoInfo,@PathVariable("userId") Long userId){
        promoInfo.setCreateUser(userId);
        promoInfo.setUpdateUser(userId);

        promoService.save(promoInfo);
        return DataResult.success(false);
    }

    @ApiOperation(value = "保存活动信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "path", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "promoName", value = "活动名称", paramType = "form", required = true, dataType = "String"),
            @ApiImplicitParam(name = "promoType", value = "促销类别ID", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "productId", value = "商品id", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "skuId", value = "skuId", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "storeId", value = "店铺", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "beginTime", value = "起始时间", paramType = "form", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "form", required = true, dataType = "String"),
            @ApiImplicitParam(name = "promoPrice", value = "活动价格", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "isApply", value = "是否需要申请", paramType = "form", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "stockQuantity", value = "活动商品库存数量", paramType = "form", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "sellQuantity", value = "活动商品销售数量", paramType = "form", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limitQuantity", value = "限购数量", paramType = "form", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "savePromo/{userId}",method = RequestMethod.POST)
    public DataResult<Boolean> savePromo(@PathVariable("userId") Long userId,
                                         @RequestParam("promoName")String promoName,
                                         @RequestParam("promoType")Long promoType,
                                         @RequestParam("productId")Long productId,
                                         @RequestParam("skuId")Long skuId,
                                         @RequestParam("storeId")Long storeId,
                                         @RequestParam("beginTime")String beginTime,
                                         @RequestParam("endTime")String endTime,
                                         @RequestParam("promoPrice")Long promoPrice,
                                         @RequestParam("isApply")Boolean isApply,
                                         @RequestParam("stockQuantity")Integer stockQuantity,
                                         @RequestParam("sellQuantity")Integer sellQuantity,
                                         @RequestParam("sellQuantity")Integer limitQuantity){

        PromoInfo promoInfo = new PromoInfo();
        promoInfo.setCreateUser(userId);
        promoInfo.setUpdateUser(userId);
        promoInfo.setPromoName(promoName);
        promoInfo.setStockQuantity(stockQuantity);
        promoInfo.setBeginTime(DateUtil.parse(beginTime).getTime());
        promoInfo.setEndTime(DateUtil.parse(endTime).getTime());
        promoInfo.setIsApply(isApply);
        promoInfo.setSellQuantity(sellQuantity);
        promoInfo.setLimitQuantity(limitQuantity);
        promoInfo.setPromoPrice(promoPrice);
        promoInfo.setState(1);
        promoInfo.setStoreId(storeId);
        promoInfo.setProductId(productId);
        promoInfo.setSkuId(skuId);
        promoInfo.setPromoType(promoType);

        promoService.save(promoInfo);
        return DataResult.success(false);
    }


    @ApiOperation(value = "获取单个活动详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "活动ID", paramType = "path", required = true, dataType = "Long")
    })
    @RequestMapping(value = "findById/{id}",method = RequestMethod.POST)
    public DataResult<PromoInfo> findById(@PathVariable("id") Long id){
        return DataResult.success(promoService.findById(id));
    }



}
