package com.miaoshasha.logcenter.mapper;

import com.miaoshasha.common.entity.system.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-24
 * Time：16:17
 * ================================
 */
@Mapper
public interface GatewayLogMapper {
    /**
     * 保存对象
     * @param log
     * @return
     */
    int insert(SysLog log);
}
