package com.miaoshasha.logcenter.handler.gateway;

import com.miaoshasha.common.entity.system.SysLog;
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
public class GatewayLogFactory {

    /**
     * 日志持久化类型，默认记录mysql
     */
    @Value("${log.durable.type:mysql}")
    private String durableType;

    @Autowired
    private MongoGatewayLogHandler mongoLogHandler ;

    @Autowired
    private MysqlGatewayLogHandler mysqlLogHandler;

    @Autowired
    private RedisGatewayLogHandler redisLogHandler;

    @Autowired
    private EsGatewayLogHandler esLogHandler ;

    public void save(SysLog log){
        switch (DurableType.fromValue(durableType)){
            case MYSQL:
                mysqlLogHandler.save(log);
                break;
            case REDIS:
                redisLogHandler.save(log);
                break;
            case MONGO:
                mongoLogHandler.save(log);
                break;
            case ES:
                esLogHandler.save(log);
                break;
        }
    }
}
