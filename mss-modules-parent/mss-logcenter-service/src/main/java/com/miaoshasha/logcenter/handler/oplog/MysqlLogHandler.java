package com.miaoshasha.logcenter.handler.oplog;

import com.miaoshasha.common.bean.OpLog;
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
public class MysqlLogHandler implements LogHandler{

    @Autowired
    private LogService logService;


    @Override
    public void save(OpLog opLog) {
        logService.save(opLog);
    }
}
