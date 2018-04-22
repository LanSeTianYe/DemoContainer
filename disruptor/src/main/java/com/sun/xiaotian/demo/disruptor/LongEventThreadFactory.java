package com.sun.xiaotian.demo.disruptor;

import java.util.concurrent.ThreadFactory;

public class LongEventThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable);
    }
}
