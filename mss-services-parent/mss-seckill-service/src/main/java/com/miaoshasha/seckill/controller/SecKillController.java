package com.miaoshasha.seckill.controller;

import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.dto.order.PromoDTO;
import com.miaoshasha.common.entity.order.OrderInfo;
import com.miaoshasha.common.entity.order.OrderProduct;
import com.miaoshasha.common.entity.order.OrderSend;
import com.miaoshasha.seckill.service.SeckillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengchaojun on 2018/6/11.
 */
@Api(tags = "SecKillController", description = "")
@RestController
@RequestMapping(value = "seckill")
public class SecKillController extends BaseController {


    @Autowired
    private SeckillService seckillService ;

    @ApiOperation(value = "抢购api", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "path", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "promoId", value = "promoId", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "storeId", value = "门店id", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberId", value = "会员", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "payType", value = "支付方式 1-货到付款，2-网银，3-支付宝，4-微信，5-充值卡抵扣",
                    paramType = "form", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "totalAmount", value = "总价", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "payAmount", value = "实际支付价格", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sendType", value = "送货方式，1-自取，2-送货上门", paramType = "form", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "isPay", value = "是否支付", paramType = "form", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "source", value = "来源", paramType = "form", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "productId", value = "产品ID", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "skuId", value = "skuId", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "productPrice", value = "产品单价", paramType = "form", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "quantity", value = "产品数量", paramType = "form", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "addressId", value = "地址ID", paramType = "form", required = true, dataType = "Long")
    })
    @RequestMapping("/promo/{userId}")
    public DataResult<Boolean> promo(@PathVariable("userId") Long userId,
                                     @RequestParam("promoId")Long promoId,
                                     @RequestParam("storeId")Long storeId,
                                     @RequestParam("memberId")Long memberId,
                                     @RequestParam("payType")Integer payType,
                                     @RequestParam("totalAmount")Long totalAmount,
                                     @RequestParam("payAmount")Long payAmount,
                                     @RequestParam("sendType")Integer sendType,
                                     @RequestParam("isPay")Boolean isPay,
                                     @RequestParam("source")Integer source,
                                     @RequestParam("productId")Long productId,
                                     @RequestParam("skuId")Long skuId,
                                     @RequestParam("productPrice")Long productPrice,
                                     @RequestParam("quantity")Integer quantity,
                                     @RequestParam("addressId")Long addressId){
        PromoDTO promoDTO = new PromoDTO();
        promoDTO.setUserId(userId);
        promoDTO.setPromoId(promoId);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateUser(userId);
        orderInfo.setUpdateUser(userId);
        orderInfo.setIsPay(isPay);
        orderInfo.setMemberId(memberId);
        orderInfo.setPayAmount(payAmount);
        orderInfo.setPayType(payType);
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setStoreId(storeId);
        orderInfo.setSendType(sendType);
        orderInfo.setSource(source);
        orderInfo.setNotes("测试秒杀");
        promoDTO.setOrderInfo(orderInfo);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductId(productId);
        orderProduct.setProductPrice(productPrice);
        orderProduct.setQuantity(quantity);
        orderProduct.setSkuId(skuId);
        promoDTO.setOrderProduct(orderProduct);

        OrderSend orderSend = new OrderSend();
        orderSend.setAddressId(addressId);
        promoDTO.setOrderSend(orderSend);

        seckillService.doSecKill(promoDTO);
        return DataResult.success(true);
    }
}
