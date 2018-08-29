package com.miaoshasha.logcenter.handler.gateway;

import com.miaoshasha.common.entity.system.SysLog;
import com.miaoshasha.logcenter.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-24
 * Time：14:59
 * ================================
 */
@Component
public class MysqlGatewayLogHandler implements GatewayLogHandler{

    @Autowired
    private LogService logService;


    @Override
    public void save(SysLog log) {
        logService.saveGatewayLog(log);
    }
}
