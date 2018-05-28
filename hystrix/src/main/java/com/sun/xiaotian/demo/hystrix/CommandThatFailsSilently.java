package com.sun.xiaotian.demo.hystrix;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandThatFailsSilently extends HystrixCommand<Boolean> {

    private final boolean throwException;

    public CommandThatFailsSilently(boolean throwException) {
        super(HystrixCommandGroupKey.Factory.asKey("Example Group"));
        this.throwException = throwException;
    }

    @Override
    protected Boolean run() throws Exception {
        if (throwException) {
            throw new RuntimeException("failure from CommandThatFailsFast");
        } else {
            return true;
        }
    }

    @Override
    protected Boolean getFallback() {
        return false;
    }
}
