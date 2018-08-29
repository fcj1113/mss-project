package com.miaoshasha.common.utils;

import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.exception.SystemException;

/**
 * Created by fengchaojun on 2018/8/1.
 */
public class AssertUtil {

    /**
     * 较验DataResult是否失败。失败则报错。
     *
     * @param result 调用结果
     * @param <T>    泛型
     */
    public static <T> void assertResult(DataResult<T> result) {
        if (result == null) {
            throw new SystemException(ErrorCode.DATA_NULL_ERROR);
        }
        if (result.isFailed()) {
            throw new SystemException(result.getStatus().getRetCode(), result.getStatus().getRetMsg());
        }
    }

}
