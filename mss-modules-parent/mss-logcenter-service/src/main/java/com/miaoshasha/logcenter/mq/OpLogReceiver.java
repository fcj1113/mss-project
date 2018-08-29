package com.miaoshasha.logcenter.mq;

import com.alibaba.fastjson.JSON;
import com.miaoshasha.common.bean.OpLog;
import com.miaoshasha.common.mq.RabbitConstants;
import com.miaoshasha.common.utils.Sequence;
import com.miaoshasha.logcenter.handler.oplog.OpLogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 日志MQ消费者
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-23
 * Time：19:07
 * ================================
 */

@Component
@RabbitListener(bindings = {
        @QueueBinding(value =
        @Queue(value = RabbitConstants.OP_LOG_QUEUE_NAME, durable = "true", autoDelete = "false", exclusive = "false"),
                exchange = @Exchange(value = RabbitConstants.LOG_EXCHANGE_NAME,durable = "true"))})
public class OpLogReceiver {

    private static final Logger logger = LoggerFactory.getLogger(OpLogReceiver.class);

    @Autowired
    private OpLogFactory opLogFactory ;

    @Autowired
    private Sequence sequence;

    @RabbitHandler
    public void process(OpLog opLog) {
        if (opLog != null) {
            logger.debug("-------------记录日志队列内容：" + JSON.toJSONString(opLog));
            opLog.setLogId(sequence.nextId());
            opLogFactory.save(opLog);
        }
    }
}
