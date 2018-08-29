package com.miaoshasha.logcenter.handler.oplog;

import com.miaoshasha.common.bean.OpLog;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-23
 * Time：18:35
 * ================================
 */
public interface LogHandler {

    void save(OpLog opLog);

}
