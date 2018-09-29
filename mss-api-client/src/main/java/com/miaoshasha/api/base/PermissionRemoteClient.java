package com.miaoshasha.api.base;


import com.miaoshasha.common.api.base.PermissionControllerApi;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.entity.permission.PermissionResource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "base-service",path = "base-service")
public interface PermissionRemoteClient extends PermissionControllerApi {

}
