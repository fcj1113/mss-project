package com.miaoshasha.common.annotation;

import com.miaoshasha.common.enums.OpType;

import java.lang.annotation.*;

/**
 * 记录操作日志
 * Created by fengchaojun on 2018/3/20.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OpLog {

    /**
     * 操作的用户id，支持EL表达式
     * @return
     */
    String userId();

    /**
     * 操作类型，A-增加，D-删除，U-修改，I-登录，O-登出，Q-查询
     * @return
     */
    OpType opType();

    /**
     * 描述，说明
     * @return
     */
    String notes() default "";

    /**
     * 功能代码，默认为空
     * @return
     */
    String funcCode() default "";;

    /**
     * 功能名称，默认为空
     * @return
     */
    String funcName() default "";

    /**
     * 获取数据的方法名称，全路径类名+方法名，若没有类名，默认执行当期类中的方法，考虑到性能问题要求此方法必须走缓存
     * @return
     */
    String methodName() default "";

    /**
     * 与methondName对应的方法内的参数
     * @return
     */
    String methodParam() default "";


}
