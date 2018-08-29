package com.miaoshasha.common.exception;

/**
 * Created by fengcj on 2017/9/12.
 */
public class ViewException extends RuntimeException {
    /**
     * 状态码
     */
    private int retCode;

    /**
     * 状态描述
     */
    private String retMsg;

    private String url ;

    public ViewException(int retCode, String retMsg,String url) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.url = url ;
    }

    public ViewException setCause(Throwable t) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
