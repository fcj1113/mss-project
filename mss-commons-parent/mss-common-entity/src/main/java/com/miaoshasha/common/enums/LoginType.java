package com.miaoshasha.common.enums;

/**
 * Created by fengchaojun on 2018/5/11.
 */
public enum LoginType {


    ALL(0, "全部"),

    PHONE(1, "电话号码"),

    USERNAME(2, "用户名"),

    EMAIL(3, "邮箱");

    private Integer value;
    private String label;

    LoginType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getLabel() {
        return label;
    }

    public boolean equals(Integer val) {
        return this.value.equals(val);
    }

    public static LoginType getElem(Integer value) {
        if (null == value) {
            return null;
        }

        for (LoginType elem : LoginType.values()) {
            if (elem.equals(value)) {
                return elem;
            }
        }
        throw new IllegalArgumentException(LoginType.class.getName() + "：非法参数值=" + value);
    }

    public static boolean isContain(Integer value) {
        for (LoginType elem : LoginType.values()) {
            if (elem.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
