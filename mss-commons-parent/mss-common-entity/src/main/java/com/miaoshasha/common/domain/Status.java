package com.miaoshasha.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by fengchaojun on 2017/9/5.
 */
public class Status implements Serializable {
    private static final long serialVersionUID = 93275177068388705L;

    @JsonProperty("retCode")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int retCode = 0;
    @JsonProperty("retMsg")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String retMsg;

    public Status() {
        this.retCode = 0;
    }

    /**
     * @param retCode
     * @param retMsg
     */
    public Status(int retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    @Override
    public String toString() {
        return "{\"retCode\":" + retCode + ",\"retMsg\":\"" + retMsg + "\"}";
    }

    public int getRetCode() {
        return retCode;
    }

    /**
     * 注意该种方式只在是使用序列化框架是使用，如果编码是调用请使用
     * <p/>
     * <code>
     * setStatusCode(int retCode, boolean appendSysCode)
     * </code>
     *
     * @param retCode
     */
    private void setStatusCode(int retCode) {
        this.retCode = retCode;
    }

    /**
     * 注意编程式方式调用
     *
     * @param retCode
     */
    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
