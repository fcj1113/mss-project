package com.miaoshasha.logcenter.handler.gateway;

import com.miaoshasha.common.entity.system.SysLog;
import com.miaoshasha.logcenter.config.GatewayLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-24
 * Time：14:48
 * ================================
 */
@Component
public class MongoGatewayLogHandler implements GatewayLogHandler{


    @Autowired
    private GatewayLogRepository gatewayLogRepository ;

    /**
     * 保存日志
     * @param log
     */
    public void save(SysLog log){
        gatewayLogRepository.save(log);
    }
}
