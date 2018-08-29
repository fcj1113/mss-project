package com.miaoshasha.common.domain;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * proper文件读取
 * Created by Administrator on 2017/7/26.
 */
public class Prop {
    private static Properties properties = null;

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 读取classpath下properties文件
     *
     * @param fileName 文件名称
     */
    public Prop(String fileName) {
        properties = new Properties();
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream in;
            if (classLoader != null) {
                in = classLoader.getResourceAsStream(fileName);
            } else {
                in = ClassLoader.getSystemResourceAsStream(fileName);
            }
            properties.load(new InputStreamReader(in, CHARSET_NAME));
            in.close();
        } catch (FileNotFoundException e) {
            //LOGGER.error(fileName + "文件不存在");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
           // LOGGER.error(fileName + "文件读取错误");
        }
    }

    /**
     * 根据KEY获取值
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        if (properties.containsKey(key)) {
            String value = properties.getProperty(key);
            return value;
        } else {
            return "";
        }
    }

}
