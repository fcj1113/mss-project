package com.miaoshasha.common.event;

import com.miaoshasha.common.bean.OpLog;
import org.springframework.context.ApplicationEvent;

/**
 * Created by fengchaojun on 2018/6/5.
 */
public class OplogEvent extends ApplicationEvent {

    public OplogEvent(OpLog opLog) {
        super(opLog);
    }
}
