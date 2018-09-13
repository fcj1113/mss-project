package com.miaoshasha.common.enums;

/**
 * Created by fengchaojun on 2018/5/11.
 */
public enum ErrorCode {

    SYSTEM_ERROR(1001, "系统错误"),
    CALC_MD5_ERROR(1002, "calc MD5 error"),
    SYSTEM_LIMITER(1003, "请求量超过系统限制"),
    API_LIMITER(1004, "接口的请求量超过限制"),
    ILLEGAL_CONCURRENT_CALL(1005, "非法重复调用"),
    TIME_OUT(1006, "系统超时"),
    CONNECTION_TIME_OUT(1007, "连接超时"),
    VERIFY_FAILED(1008, "验签失败"),
    VERIFY_EXPIRE(1009, "签名已过期"),
    UNZIP_FAILED(1010, "解压失败"),
    ENCODER_FAILED(1011, "编码失败"),
    DECODER_FAILED(1012, "解码失败"),
    CONNECT_ERROR(1013, "连接异常"),
    MISSING_SERVER_PROVIDER(1014, "缺失服务提供者"),
    ILLEGAL_CALL(1015, "非法请求"),
    SYSTEM_ERROR_NULL_POINTER(1016, "系统错误:空对象"),

    PARAM_ERROR(1500, "参数错误"),
    NOT_SUPPORT_METHOD(1501, "不支持的调用"),
    NOT_SUPPORT_OPERATOR(1502, "不支持的操作"),
    SERVER_REFUSE(1502, "服务拒绝"),
    ILLEGAL_ARGUMENT(1503, "非法参数"),
    JSON_SERIALIZE_FAIL(1504, "json序列化失败"),
    CONCURRENT_CACHE_KEY_TIMEOUT(1505, "并发缓存请求超时"), // cache穿透并发超时
    PARAM_MISSING(1506, "缺少参数"),

    /*业务层错误码*/
    DATA_NULL_ERROR(5000, "返回数据为空"),
    PASSWORD_ERROR(5001, "密码错误"),
    PERMISSION_NO_ERROR(5002,"权限不足"),
    /**业务执行失败错误码**/
    EXECUTE_FAIL(6000,"业务执行失败")
    ;
    private int code;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static boolean isMember(int code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (code == errorCode.getCode()) {
                return true;
            }
        }
        return false;
    }
}
