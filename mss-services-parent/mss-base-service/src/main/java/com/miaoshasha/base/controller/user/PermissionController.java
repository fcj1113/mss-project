package com.miaoshasha.base.controller.user;

import com.miaoshasha.base.controller.BaseController;
import com.miaoshasha.base.service.user.PermissionService;
import com.miaoshasha.common.api.base.PermissionControllerApi;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.entity.permission.PermissionResource;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController implements PermissionControllerApi {

    @Autowired
    private PermissionService permissionService;


    @ApiOperation(value = "根据角色获取权限信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", paramType = "query", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "resType", value = "资源类型", paramType = "query", required = false, dataType = "Integer")
    })
    public DataResult<List<PermissionResource>> findByRole(@RequestParam("roleId") Long roleId,
                                                           @RequestParam(value = "resType",required = false) Integer resType) {
        List<PermissionResource> list = permissionService.findByRole(roleId, resType);
        return DataResult.success(list);
    }

    @ApiOperation(value = "获取用户的权限信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "path", required = true, dataType = "Long")
    })
    public DataResult<List<PermissionResource>> findByUserId(@PathVariable("userId") Long userId) {
        List<PermissionResource> list = permissionService.findByUserId(userId);
        return DataResult.success(list);
    }



}
