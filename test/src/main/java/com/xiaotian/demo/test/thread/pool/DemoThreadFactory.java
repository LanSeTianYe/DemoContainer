package com.xiaotian.demo.test.thread.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 */
public class DemoThreadFactory implements ThreadFactory {

    private static final AtomicInteger ID = new AtomicInteger(0);
    private static final int DELTA = 1;
    private final String name;

    public DemoThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(name + "-" + ID.getAndAdd(DELTA));
        thread.setDaemon(false);
        return thread;
    }
}
