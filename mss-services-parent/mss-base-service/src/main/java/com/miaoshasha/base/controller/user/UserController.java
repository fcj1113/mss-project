package com.miaoshasha.base.controller.user;

import cn.hutool.core.lang.Assert;
import com.miaoshasha.base.controller.BaseController;
import com.miaoshasha.base.service.user.RoleService;
import com.miaoshasha.base.service.user.UserService;
import com.miaoshasha.common.annotation.OpLog;
import com.miaoshasha.common.component.token.TokenManager;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.dto.user.UserDTO;
import com.miaoshasha.common.entity.user.Role;
import com.miaoshasha.common.entity.user.User;
import com.miaoshasha.common.enums.OpType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fengchaojun on 2018/3/6.
 */
@Api(tags = "UserController", description = "用户服务")
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TokenManager tokenManager;


    @ApiOperation(value = "获取用户详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "path", required = true, dataType = "Long")
    })
    @RequestMapping(value = "/getInfoById/{userId}")
    public DataResult<UserDTO> getInfoById(@PathVariable("userId") Long userId) {
        User user = Assert.notNull( userService.findById(userId), "用户信息为空");

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);

        //查询角色信息
//        Role role = Assert.notNull(roleService.getRoleByUserId(user.getUserId()),"用户角色信息为空");
//        BeanUtils.copyProperties(role, userDTO);

        return DataResult.success(userDTO);
    }

    @ApiOperation(value = "获取用户的角色信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "path", required = true, dataType = "Long")
    })
    @RequestMapping(value = "/getRoleByUserId/{userId}")
    public DataResult<Role> getRoleByUserId(@PathVariable("userId") Long userId) {
        return DataResult.success(roleService.getRoleByUserId(userId));
    }

    @ApiOperation(value = "根据手机号码获取用户信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNo", value = "登录手机号码", paramType = "form", required = true, dataType = "String"),
            @ApiImplicitParam(name = "channel", value = "渠道", paramType = "form", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/getUserByPhoneNo")
    public DataResult<UserDTO> getUserByPhoneNo(@RequestParam("phoneNo") String phoneNo,
                                                @RequestParam("channel") Integer channel) {
        User user = userService.getUserByPhoneNo(phoneNo, channel);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        //查询角色信息
        Role role = roleService.getRoleByUserId(user.getUserId());
        BeanUtils.copyProperties(role, userDTO);
        return DataResult.success(userDTO);
    }


    @ApiOperation(value = "登出", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "登录手机号码", paramType = "path", required = true, dataType = "long")
    })
    @PostMapping(value = "/logout/{userId}")
    @OpLog(userId = "{userId}", notes = "登出", opType = OpType.LOGOUT)
    public DataResult<Boolean> logout(@PathVariable("userId") long userId, @RequestParam("token") String token) {
        tokenManager.deleteToken(token);
        return DataResult.success(true);
    }


}
