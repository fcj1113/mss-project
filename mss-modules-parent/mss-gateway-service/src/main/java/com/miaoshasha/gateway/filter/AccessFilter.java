/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.miaoshasha.gateway.filter;

import cn.hutool.core.util.ReUtil;
import com.miaoshasha.api.base.PermissionRemoteClient;
import com.miaoshasha.common.component.token.RedisTokenManager;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.entity.permission.PermissionResource;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.exception.SystemException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;

/**
 * @author lengleng
 * @date 2017/11/20
 * 在RateLimitPreFilter 之前执行，不然又空指针问题
 */
@Slf4j
@Component
public class AccessFilter extends ZuulFilter {

    @Value("${zuul.ignore.uri}")
    private String ignoreUri;

    public static final String AUTH_HEADER = "Authorization";

    @Autowired
    private RedisTokenManager redisTokenManager;

    @Autowired
    private PermissionRemoteClient permissionRemoteClient;


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime", System.currentTimeMillis());

        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        //1.跳过不进行过滤的uri
        log.info("----------uri=" + uri);
        if (isMatch(uri)) {
            return null;
        }
        //2.判断token是否有效
        String token = request.getHeader(AUTH_HEADER);
        log.debug("----------token=" + token);
        ctx.addZuulRequestHeader(AUTH_HEADER, redisTokenManager.verifyToken(token));

        //3.根据用户信息查询相应的权限信息，并对uri进行判断是否有权限
        checkPermission(uri, token);
        return null;
    }


    /**
     * url是否进行匹配
     *
     * @param uri
     * @return
     */
    public boolean isMatch(String uri) {
        if (StringUtils.isEmpty(ignoreUri)) {
            return false;
        }

        String[] ignoreUriArr = ignoreUri.split(",");
        boolean retFlag = false;
        for (String ignoreUri : ignoreUriArr) {
            log.info("---uri.startsWith(ignoreUri)=" + uri.startsWith(ignoreUri));

            if (!StringUtils.isEmpty(ignoreUri) && (uri.equals(ignoreUri) || ReUtil.isMatch(ignoreUri, uri) || uri.startsWith(ignoreUri))) {
                retFlag = true;
                break;
            }
        }

        return retFlag;
    }

    public void checkPermission(String uri, String token) {
        Long userId = redisTokenManager.findUserIdByToken(token);
        DataResult<List<PermissionResource>> permissionList = permissionRemoteClient.findByUserId(userId);
        if (permissionList.getResult() != null && permissionList.getResult().size() > 0) {
            boolean flag = false;
            for (PermissionResource permissionResource : permissionList.getResult()) {
                if (permissionResource.getEntryPath().equals(uri)) {
                    flag = true;
                    break;
                }
            }
            //无权限
            if (!flag) {
                throw new SystemException(ErrorCode.PERMISSION_NO_ERROR);
            }
        } else {
            throw new SystemException(ErrorCode.PERMISSION_NO_ERROR);
        }

    }

}
