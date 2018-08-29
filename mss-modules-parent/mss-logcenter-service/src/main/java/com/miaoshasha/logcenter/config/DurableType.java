package com.miaoshasha.logcenter.config;

import com.miaoshasha.common.base.BaseEnum;

/**
 *
 * 日志持久化类型
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-23
 * Time：20:48
 * ================================
 */
public enum DurableType{

    MYSQL("mysql"),
    REDIS("redis"),
    MONGO("mongo"),
    ES("es");

    private String value ;

    DurableType(String value){
       this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DurableType fromValue(String value) {
        for (DurableType type : DurableType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

}
