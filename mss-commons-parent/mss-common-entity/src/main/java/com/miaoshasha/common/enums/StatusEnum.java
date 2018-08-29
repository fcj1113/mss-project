package com.miaoshasha.common.enums;

/**
 * 启停状态枚举类
 * @author
 */
public enum StatusEnum {

    /**
     * 已删除 -1
     */
    REMOVE(-1, "已删除"),
    /**
     * 未启用：0
     */
    ZERO(0, "未启用"),
    /**
     * 已启用：1
     */
    ENABLED(1, "启用"),
    /**
     * 已停用：2
     */
    DISABLED(2, "停用");

    private Integer value;
    private String desc;

    StatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return desc;
    }

    public boolean equals(Integer val) {
        return this.value.equals(val);
    }

    public static StatusEnum getElem(Integer value) {
        if (null == value) {
            return null;
        }

        for (StatusEnum elem : StatusEnum.values()) {
            if (elem.equals(value)) {
                return elem;
            }
        }
        throw new IllegalArgumentException(StatusEnum.class.getName() + "：非法参数值=" + value);
    }

    public static boolean isContain(Integer value) {
        for (StatusEnum elem : StatusEnum.values()) {
            if (elem.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
