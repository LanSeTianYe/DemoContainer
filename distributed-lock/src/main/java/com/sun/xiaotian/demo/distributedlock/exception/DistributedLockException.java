package com.sun.xiaotian.demo.distributedlock.exception;

public class DistributedLockException extends RuntimeException {

    public DistributedLockException() {
        super();
    }

    public DistributedLockException(String message) {
        super(message);
    }

    public DistributedLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributedLockException(Throwable cause) {
        super(cause);
    }

    protected DistributedLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
