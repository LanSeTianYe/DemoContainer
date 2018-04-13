package com.sun.xiaotian.demo.test.exception;


/**
 * 参数格式错误时返回的异常
 */
public class ParameterFormatErrorException extends WxBaseException {

    public ParameterFormatErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}
