package com.miaoshasha.common.entity.system;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志
 */
@Data
@Document(collection = "log_gateway")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private Long logId;

    /**
     * 创建者
     */
    @Indexed
    private String userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 操作IP地址
     */
    private String ipAddr;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 操作方式
     */
    private String method;
    /**
     * 操作提交的数据
     */
    private String params;

    /**
     * 执行时长
     */
    private Long duration;

    /**
     * 异常信息
     */
    private String error;


}
