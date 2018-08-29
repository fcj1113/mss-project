package com.miaoshasha.base.controller.common;

import com.miaoshasha.base.controller.BaseController;
import com.miaoshasha.common.domain.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengchaojun on 2018/6/11.
 */
@Api(tags = "CommonController", description = "通用服务")
@RequestMapping(value = "common")
@RestController
public class CommonController extends BaseController {

    /**
     * 获取系统时间
     *
     * @param retType 返回格式，默认为毫秒,1-返回秒
     * @return
     */
    @ApiOperation(value = "获取系统时间", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "retType", value = "返回格式，默认为毫秒", paramType = "path", required = false, dataType = "Integer")
    })
    @RequestMapping(value = "getCurrentTime/{retType}", method = RequestMethod.POST)
    public DataResult<Long> getCurrentTime(@PathVariable(value = "retType", required = false) Integer retType) {
        Long currTime = null;
        if (StringUtils.isEmpty(retType)) {//毫秒
            currTime = System.currentTimeMillis();
        } else if (retType == 1) {//秒
            currTime = System.currentTimeMillis() / 1000;
        }
        return DataResult.success(currTime);
    }
}
