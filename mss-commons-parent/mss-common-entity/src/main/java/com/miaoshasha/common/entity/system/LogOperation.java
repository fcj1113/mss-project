package com.miaoshasha.common.entity.system;

import com.miaoshasha.common.base.BaseEntity;
import lombok.Data;

/**
 * Created by fengchaojun on 2018/5/29.
 */
@Data
public class LogOperation implements BaseEntity {

    private Long logId ;
    private Long userId;
    private String userName;
    private Long opTime ;
    private String ipAddress;
    private String opType ;
    /**
     * 记录历史结果
     */
    private String historyResult;

    /**
     * 记录最后一次结果
     */
    private String lastResult;
    private String notes ;
    private String funcCode ;
    private String funcName ;

    @Override
    public Long getId() {
        return this.logId;
    }
}
