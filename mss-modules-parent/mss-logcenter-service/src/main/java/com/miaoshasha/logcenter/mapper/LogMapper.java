package com.miaoshasha.logcenter.mapper;

import com.miaoshasha.common.bean.OpLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-23
 * Time：19:47
 * ================================
 */

@Mapper
public interface LogMapper{

    /**
     * 保存对象
     * @param opLog
     * @return
     */
    int insert(OpLog opLog);
}
