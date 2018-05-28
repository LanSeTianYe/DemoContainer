package com.sun.xiaotian.demo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;


public class CommandThatFailsFast extends HystrixCommand<Boolean> {

    private final boolean throwException;

    public CommandThatFailsFast(boolean throwException) {
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
}
