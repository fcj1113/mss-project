package com.miaoshasha.logcenter.handler.oplog;

import com.miaoshasha.common.bean.OpLog;
import com.miaoshasha.logcenter.config.DurableType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-24
 * Time：15:57
 * ================================
 */
@Slf4j
@Service
public class OpLogFactory {

    /**
     * 日志持久化类型，默认记录mysql
     */
    @Value("${log.durable.type:mysql}")
    private String durableType;

    @Autowired
    private MongoLogHandler mongoLogHandler ;

    @Autowired
    private MysqlLogHandler mysqlLogHandler;

    @Autowired
    private RedisLogHandler redisLogHandler;

    @Autowired
    private EsLogHandler esLogHandler ;

    public void save(OpLog opLog){
        switch (DurableType.fromValue(durableType)){
            case MYSQL:
                mysqlLogHandler.save(opLog);
                break;
            case REDIS:
                redisLogHandler.save(opLog);
                break;
            case MONGO:
                mongoLogHandler.save(opLog);
                break;
            case ES:
                esLogHandler.save(opLog);
                break;
        }
    }
}
