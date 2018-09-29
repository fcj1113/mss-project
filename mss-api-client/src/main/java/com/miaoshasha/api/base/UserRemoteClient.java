package com.miaoshasha.api.base;

import com.miaoshasha.common.api.base.UserControllerApi;
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
public interface UserRemoteClient extends UserControllerApi {


}
