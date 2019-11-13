package com.xiaotian.demo.springboot.transactional.exception;

public class TransactionBusinessException extends RuntimeException {

    public TransactionBusinessException() {
    }

    public TransactionBusinessException(String message) {
        super(message);
    }

    public TransactionBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionBusinessException(Throwable cause) {
        super(cause);
    }

    public TransactionBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
