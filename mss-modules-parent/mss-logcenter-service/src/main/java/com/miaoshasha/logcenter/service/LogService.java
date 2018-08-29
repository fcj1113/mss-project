package com.miaoshasha.logcenter.service;

import com.miaoshasha.common.bean.OpLog;
import com.miaoshasha.common.entity.system.SysLog;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-23
 * Time：19:45
 * ================================
 */
public interface LogService {

    /**
     * 持久化日志
     * @param opLog
     */
    void save(OpLog opLog);

    void saveGatewayLog(SysLog sysLog);
}
