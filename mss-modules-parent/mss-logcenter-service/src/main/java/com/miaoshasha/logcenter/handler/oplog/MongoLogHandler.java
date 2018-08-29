package com.miaoshasha.logcenter.handler.oplog;

import com.miaoshasha.common.bean.OpLog;
import com.miaoshasha.logcenter.config.OpLogRepository;
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
public class MongoLogHandler implements LogHandler{


    @Autowired
    private OpLogRepository logRepository ;

    /**
     * 保存日志
     * @param opLog
     */
    public void save(OpLog opLog){
        logRepository.save(opLog);
    }
}
