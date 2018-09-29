package com.miaoshasha.api.auth;

import com.miaoshasha.common.api.auth.AuthControllerApi;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.dto.user.UserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mss-auth-service",path = "mss-auth-service")
public interface AuthRemoteClient  extends AuthControllerApi {

}
