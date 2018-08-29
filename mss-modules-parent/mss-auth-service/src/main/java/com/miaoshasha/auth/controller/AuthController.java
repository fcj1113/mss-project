package com.miaoshasha.auth.controller;

import com.miaoshasha.auth.service.AuthService;
import com.miaoshasha.common.annotation.OpLog;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.dto.user.UserDTO;
import com.miaoshasha.common.enums.OpType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fengchaojun on 2018/8/1.
 */
@Api(tags = "AuthController", description = "授权服务")
@Slf4j
@RestController
@RequestMapping(value = "/")
public class AuthController {

    @Autowired
    private AuthService authService;


    @ApiOperation(value = "登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNo", value = "登录手机号码", paramType = "form", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码", paramType = "form", required = true, dataType = "String"),
            @ApiImplicitParam(name = "channel", value = "渠道", paramType = "form", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @OpLog(userId = "#phoneNo",notes = "登录",opType = OpType.LOGIN)
    public DataResult<UserDTO> login(@RequestParam("phoneNo") String phoneNo,
                                     @RequestParam("password") String password,
                                     @RequestParam("channel") Integer channel){
        UserDTO userDTO = authService.login(phoneNo, password, channel);
        //密码不返回
        userDTO.setPassword(null);
        return DataResult.success(userDTO);
    }

    @ApiOperation(value = "根据token获取登录信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "TOKEN", paramType = "form", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getUserByToken",method = RequestMethod.POST)
    @OpLog(userId = "#phoneNo",notes = "根据token获取登录信息",opType = OpType.LOGIN)
    public DataResult<UserDTO> getUserByToken(@RequestParam("token") String token){
        return DataResult.success(authService.getUserByToken(token));
    }

    @ApiOperation(value = "校验token有效性", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "TOKEN", paramType = "form", required = true, dataType = "String")
    })
    @PostMapping(value = "/verifyToken")
    public DataResult<Boolean> verifyToken(@RequestParam("token") String token){
        return DataResult.success(authService.verifyToken(token));
    }
}
