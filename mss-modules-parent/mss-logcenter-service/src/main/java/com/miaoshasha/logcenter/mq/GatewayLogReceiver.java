package com.miaoshasha.logcenter.mq;

import com.alibaba.fastjson.JSON;
import com.miaoshasha.common.entity.system.SysLog;
import com.miaoshasha.common.mq.RabbitConstants;
import com.miaoshasha.common.utils.Sequence;
import com.miaoshasha.logcenter.handler.gateway.GatewayLogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-24
 * Time：16:15
 * ================================
 */

@Component
@RabbitListener(bindings = {
        @QueueBinding(value =
        @Queue(value = RabbitConstants.GATEWAY_LOG_QUEUE_NAME, durable = "true", autoDelete = "false", exclusive = "false"),
                exchange = @Exchange(value = RabbitConstants.LOG_EXCHANGE_NAME, durable = "true"))})
public class GatewayLogReceiver {

    private static final Logger logger = LoggerFactory.getLogger(GatewayLogReceiver.class);

    @Autowired
    private GatewayLogFactory gatewayLogFactory;

    @Autowired
    private Sequence sequence;

    @RabbitHandler
    public void process(SysLog log) {
        if (log != null) {
            log.setLogId(sequence.nextId());
            logger.debug("-------------记录日志队列内容：" + JSON.toJSONString(log));
            gatewayLogFactory.save(log);
        }
    }

}
