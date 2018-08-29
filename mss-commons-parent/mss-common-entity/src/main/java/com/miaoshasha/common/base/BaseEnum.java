package com.miaoshasha.common.base;

/**
 * 枚举基类
 *
 * @author：fengchaojun <br/>
 * ===============================
 * Created with IDEA.
 * Date：2018-08-21
 * Time：16:11
 * ================================
 */
public interface BaseEnum<V> {

    V getValue();

    String getLabel();
}
