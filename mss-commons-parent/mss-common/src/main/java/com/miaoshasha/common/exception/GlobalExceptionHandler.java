package com.miaoshasha.common.exception;

import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.domain.Status;
import com.miaoshasha.common.enums.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 * Created by fengchaojun on 2017/9/7.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static final String RET_CODE = "retCode";
    public static final String RET_MSG = "retMsg";

    /**
     * 异常处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public DataResult<?> exception(HttpServletRequest request, Exception ex) {
        LOGGER.error("Exception[" + ex.getMessage() + "]");
        return dataResult(request, ErrorCode.SYSTEM_ERROR);
    }


    /**
     * 视图异常，跳转到页面
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ViewException.class)
    public ModelAndView viewException(ViewException ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("retCode", ex.getRetCode());
        mv.addObject("retMsg", ex.getRetMsg());
        mv.setViewName(ex.getUrl());
        return mv;
    }

    /**
     * 自定义系统异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = SystemException.class)
    public DataResult<?> systemException(HttpServletRequest request, SystemException ex) {
        LOGGER.error("retCode[" + ex.getRetCode() + "],retMsg[" + ex.getRetMsg() + "]");
        DataResult<?> dataResult = DataResult.faild(ex.getRetCode(), ex.getRetMsg());
        this.requestError(request, dataResult.getStatus());
        return dataResult;
    }

    /**
     * 非法参数
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public DataResult<?> illegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
        LOGGER.error("IllegalArgumentException[" + ex.getMessage() + "]");
        return dataResult(request, ErrorCode.ILLEGAL_ARGUMENT);
    }

    /**
     * 运行时异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public DataResult<?> runtimeException(HttpServletRequest request, RuntimeException ex) {
        LOGGER.error("RuntimeException[" + ex.getMessage() + "]");
        return dataResult(request, ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 空指针异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public DataResult<?> nullPointerException(HttpServletRequest request, NullPointerException ex) {
        LOGGER.error("nullPointerException[" + ex.getMessage() + "]");
        return dataResult(request, ErrorCode.SYSTEM_ERROR_NULL_POINTER);
    }


    private void requestError(HttpServletRequest request, Status status) {
        request.setAttribute(RET_CODE, status.getRetCode());
        request.setAttribute(RET_MSG, status.getRetMsg());
    }

    private DataResult<?> dataResult(HttpServletRequest request, ErrorCode errorCode) {
        DataResult<?> dataResult = DataResult.faild(errorCode.getCode(), errorCode.getMsg());
        this.requestError(request, dataResult.getStatus());
        return dataResult;
    }
}
