package com.miaoshasha.common.event;

import com.miaoshasha.common.bean.OpLog;
import com.miaoshasha.common.utils.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by fengchaojun on 2018/6/6.
 */
@Component
public class OpLogListener implements ApplicationListener<OplogEvent> {

    private static Logger logger = LoggerFactory.getLogger(OpLogListener.class);

    //@Autowired
    //private LogRepository logRepository ;

    @Autowired
    private Sequence sequence ;

    @Override
    public void onApplicationEvent(OplogEvent oplogEvent) {

        OpLog opLog = (OpLog) oplogEvent.getSource();
        opLog.setLogId(sequence.nextId());

        logger.info("记录操作日志|USER:"+ opLog.getUserId()+"|USERNAME:"+ opLog.getUserName()+"|OPTYPE:"+ opLog.getOpType());
        //logRepository.save(opLog);
    }
}
