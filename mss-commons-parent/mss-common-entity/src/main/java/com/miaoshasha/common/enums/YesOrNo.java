package com.miaoshasha.common.enums;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-06-27
 * Time：20:32
 * -----------------------------
 */
public enum YesOrNo {
    Y("是"),
    N("否");

    private String desc;

    YesOrNo(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
