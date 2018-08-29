package com.miaoshasha.common.enums;

/**
 * Created by fengchaojun on 2018/5/31.
 */
public enum OpType {
    ADD("A"),//新增

    DELETE("D"),//删除

    UPDATE("U"),//修改

    LOGIN("I"),//登录

    QUERY("Q"),//查询

    LOGOUT("O");//登出

    private String value;

    OpType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
