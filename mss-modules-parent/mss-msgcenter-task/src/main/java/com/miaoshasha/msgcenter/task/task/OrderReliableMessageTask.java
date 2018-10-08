package com.miaoshasha.msgcenter.task.task;

import com.miaoshasha.msgcenter.task.service.OrderReliableMessageHanlderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-10-08
 * Time：11:50
 * -----------------------------
 */
@Component
@Configurable
@EnableScheduling
public class OrderReliableMessageTask {

    @Autowired
    private OrderReliableMessageHanlderImpl orderReliableMessageHanlder;

    /**
     * 每10秒执行一次
     */
    @Scheduled(fixedRate = 10000)
    public void waitingConfirmTimeOutMessages(){
        orderReliableMessageHanlder.handleWaitingConfirmTimeOutMessages();
    }

    @Scheduled(fixedRate = 10000)
    public void sendingTimeOutMessages(){
        orderReliableMessageHanlder.handleSendingTimeOutMessages();
    }
}
