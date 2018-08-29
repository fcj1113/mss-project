package com.miaoshasha.order.controller;

import com.miaoshasha.common.domain.DataResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengchaojun on 2018/7/24.
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @ApiOperation(value = "获取用户详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "path", required = true, dataType = "Long")
    })
    @RequestMapping(value = "/getInfoById/{userId}")
    public DataResult<String> getInfoById(@PathVariable("userId") Long userId) {
        String re1s = "11111111111";
        return DataResult.success(re1s);
    }
}
