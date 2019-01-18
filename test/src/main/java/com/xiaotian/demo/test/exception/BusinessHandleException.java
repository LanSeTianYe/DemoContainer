package com.xiaotian.demo.test.exception;

/**
 * 业务处理出现错误时的异常
 */
public class BusinessHandleException extends WxBaseException {

    public BusinessHandleException(String message, Throwable cause) {
        super(message, cause);
    }

}
