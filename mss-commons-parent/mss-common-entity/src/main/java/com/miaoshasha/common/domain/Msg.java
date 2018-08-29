package com.miaoshasha.common.domain;


import org.springframework.util.StringUtils;

import java.text.MessageFormat;

/**
 * 读取msg.properties文件
 * Created by Administrator on 2017/7/26.
 */
public class Msg {

    private static final String MSG_FILE = "msg_zh_CN.properties";
    private static Prop prop;

    /**
     * 获取msg信息
     *
     * @param key
     * @return
     */
    public static String getMsg(String key) {
        if (prop == null) {
            prop = new Prop(MSG_FILE);
        }
        return prop.getValue(key);
    }

    /**
     * 获取绑定参数的msg信息
     *
     * @param key
     * @param params 绑定的参数
     * @return
     */
    public static String getMsg(String key, String[] params) {
        if (prop == null) {
            prop = new Prop(MSG_FILE);
        }
        String msg = "";
        String value = prop.getValue(key);
        if (!StringUtils.isEmpty(value)) {
            msg = MessageFormat.format(value, (Object[])params);
        }
        return msg;
    }

    /**
     * 获取绑定参数的msg信息
     * @param key
     * @param param 单个参数
     * @return
     */
    public static String getMsg(String key, String param) {
        return getMsg(key,new String[]{param});
    }
}
