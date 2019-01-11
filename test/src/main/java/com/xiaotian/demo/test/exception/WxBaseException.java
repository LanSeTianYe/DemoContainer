package com.xiaotian.demo.test.exception;

/**
 * 微信公众平台基础线程类
 */
public abstract class WxBaseException extends RuntimeException {

    public WxBaseException() {
        super();
    }

    public WxBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxBaseException(Throwable cause) {
        super(cause);
    }

    public WxBaseException(String message) {
        super(message);
    }

    protected WxBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

