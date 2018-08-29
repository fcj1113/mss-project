package com.miaoshasha.logcenter.service.impl;

import com.miaoshasha.common.bean.OpLog;
import com.miaoshasha.common.entity.system.SysLog;
import com.miaoshasha.logcenter.mapper.GatewayLogMapper;
import com.miaoshasha.logcenter.mapper.LogMapper;
import com.miaoshasha.logcenter.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-23
 * Time：19:50
 * ================================
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper ;

    @Autowired
    private GatewayLogMapper gatewayLogMapper ;

    @Override
    public void save(OpLog opLog) {
        logMapper.insert(opLog);
    }

    @Override
    public void saveGatewayLog(SysLog sysLog) {
        gatewayLogMapper.insert(sysLog);
    }
}
