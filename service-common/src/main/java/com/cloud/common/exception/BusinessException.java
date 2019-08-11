package com.cloud.common.exception;


import com.cloud.common.enums.ErrorCodeEnum;

/**
 * Description: 业务异常定义
 * Date:      2019/8/11 9:04
 * author     zhuwj
 * version    V1.0
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 异常
     */
    private Exception exception;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     *
     * @param code 错误码
     * @param message 错误消息
     * @param exception 异常
     */
    public BusinessException(Integer code, String message, Exception exception) {
        this.code = code;
        this.message = message;
        this.exception = exception;
    }

    /**
     *
     * @param code  错误码
     * @param message 错误消息
     */
    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 默认错误码为1
     * @param message
     */
    public BusinessException(String message) {
        this.code = ErrorCodeEnum.BUSINESS_FAIL.getCode();
        this.message = message;
    }

    /**
     * 错误枚举类
     * @param codeEnum
     */
    public BusinessException(ErrorCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMsg();
    }
    public BusinessException() {
    }
}
