package com.miaoshasha.common.exception;

import com.miaoshasha.common.enums.ErrorCode;

/**
 * Created by fengchaojun on 2017/9/5.
 */
public class SystemException extends RuntimeException {

    /**
     * 状态码
     */
    private int retCode;
    /**
     * 状态描述
     */
    private String retMsg;

    public SystemException(int retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public SystemException(ErrorCode code, String retMsg) {
        this.retCode = code.getCode();
        this.retMsg = retMsg;
    }

    public SystemException(ErrorCode code) {
        this.retCode = code.getCode();
        this.retMsg = code.getMsg();
    }

    public SystemException setCause(Throwable t) {
        super.initCause(t);
        return this;
    }

    public int getRetCode() {
        return retCode;
    }

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
