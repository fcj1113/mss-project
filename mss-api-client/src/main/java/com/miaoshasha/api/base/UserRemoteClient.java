package com.miaoshasha.api.base;

import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.dto.user.UserDTO;
import com.miaoshasha.common.entity.user.Role;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户的远程调用
 * Created by fengchaojun on 2018/7/31.
 */
@FeignClient(value = "base-service",path = "base-service")
@RequestMapping(value = "/user")
public interface UserRemoteClient {

    @PostMapping(value = "/getInfoById/{userId}")
    public DataResult<UserDTO> getInfoById(@PathVariable("userId") Long userId);


    /**
     * 根据手机号码获取用户信息
     * @param phoneNo 登录手机号码
     * @param channel 渠道
     * @return
     */
    @PostMapping(value = "/getUserByPhoneNo" )
    public DataResult<UserDTO> getUserByPhoneNo(@RequestParam("phoneNo") String phoneNo,
                                                @RequestParam("channel") Integer channel);


    /**
     * 获取用户的角色信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getRoleByUserId/{userId}")
    public DataResult<Role> getRoleByUserId(@PathVariable("userId") Long userId);
}
