package com.miaoshasha.common.enums;

import com.miaoshasha.common.base.BaseEnum;

/**
 * 交换器枚举
 *
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-21
 * Time：16:09
 * ================================
 */
public enum ExchangeType implements BaseEnum<Integer> {
    DIRECT(1, "direct"),
    TOPIC(2, "topic"),
    FANOUT(3, "fanout");

    private Integer value;
    private String label;

    ExchangeType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
