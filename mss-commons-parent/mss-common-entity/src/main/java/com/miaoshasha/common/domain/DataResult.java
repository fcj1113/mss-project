package com.miaoshasha.common.domain;

import com.alibaba.fastjson.JSON;
import com.miaoshasha.common.enums.ErrorCode;

import java.io.Serializable;

/**
 * Created by fengchaojun on 2017/9/5.
 */
public class DataResult<T> implements Serializable{
    private static final long serialVersionUID = -727029392493896792L;

    private Status status = new Status(0, "ok");

    private T result;

    public T getResult() {
        return result;
    }
    public void setResult(T result) {
        this.result = result;
    }

    public static <T>  DataResult<T> success(Object result) {
        DataResult<T> dataResult = new DataResult<T>();
        dataResult.setResult((T) result);
        return dataResult;
    }

    public static <T> DataResult<T> success(Object result, String retMsg) {
        DataResult<T> dataResult = new DataResult<T>();
        dataResult.setStatus(new Status(0, retMsg));
        dataResult.setResult((T) result);
        return dataResult;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static <T> DataResult<T> faild(int errorCode, String errMsg) {
        DataResult<T> dataResult = new DataResult<T>();
        dataResult.setStatus(new Status(errorCode, errMsg));
        return dataResult;
    }

    public static <T> DataResult<T> faild(ErrorCode errorCode) {
        return faild(errorCode.getCode(),errorCode.getMsg());
    }

    public boolean isSuccess() {
        return getStatus().getRetCode() == 0;
    }

    /**
     * 接口返回错误
     */
    public boolean isFailed() {
        return !isSuccess();
    }

    public String toJson(){
      return  JSON.toJSONString(this);
    }
}
