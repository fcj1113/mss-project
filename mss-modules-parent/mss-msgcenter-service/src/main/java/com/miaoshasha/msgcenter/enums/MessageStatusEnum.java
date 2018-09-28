package com.miaoshasha.msgcenter.enums;

/**
 *
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-27
 * Time：10:25
 * -----------------------------
 */
public enum MessageStatusEnum {

    WAIT_VERIFY("待确认"),

    SENDING("发送中");

    private String desc;

    MessageStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
