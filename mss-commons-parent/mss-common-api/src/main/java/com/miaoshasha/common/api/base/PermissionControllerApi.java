package com.miaoshasha.common.api.base;

import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.entity.permission.PermissionResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-29
 * Time：17:07
 * -----------------------------
 */
@RequestMapping(value = "/permission")
public interface PermissionControllerApi {

    /**
     * 根据角色获取权限信息
     * @param roleId 角色id
     * @param resType 资源类型
     * @return
     */
    @PostMapping("/findByRole")
    public DataResult<List<PermissionResource>> findByRole(@RequestParam("roleId") Long roleId,
                                                           @RequestParam(value = "resType",required = false) Integer resType);

    /**
     * 查询用户的权限资源信息
     * @param userId
     * @return
     */
    @PostMapping("/findByUserId/{userId}")
    public DataResult<List<PermissionResource>> findByUserId(@PathVariable("userId") Long userId);
}
