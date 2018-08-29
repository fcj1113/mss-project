package com.miaoshasha.logcenter.handler.gateway;

import com.miaoshasha.common.entity.system.SysLog;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-24
 * Time：16:31
 * ================================
 */
public interface GatewayLogHandler {
    void save(SysLog log);
}
