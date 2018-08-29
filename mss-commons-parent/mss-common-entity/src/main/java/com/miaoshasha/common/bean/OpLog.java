package com.miaoshasha.common.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

/**
 * 默认存储在mongodb的log_operation下
 * Created by fengchaojun on 2018/5/23.
 */
@Data
@Document(collection = "log_operation")
public class OpLog implements Serializable{

    private static final long serialVersionUID = -4954398162094069647L;

    /**
     * logid取uuid
     */
    @Id
    private Long logId ;
    @Indexed
    private Long userId;
    @Indexed
    private String userName;
    private Long opTime ;
    private String ipAddress;

    //操作类型，A-增加，D-删除，U-修改，I-登录，O-登出，Q-查询
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
}
