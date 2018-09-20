package com.miaoshasha.seckill.controller;

import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.seckill.service.SeckillService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/testSeq")
    public DataResult<String> testSeq(){
        seckillService.testSeq();
        return DataResult.success("111111111111");
    }
}
