package com.xiaotian.demo.rabbitmq.core.thread;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 */

public class RabbitThreadFactory implements ThreadFactory {

    private static final AtomicInteger ID = new AtomicInteger(0);
    private static final int DELTA = 1;
    private final String threadName;

    public RabbitThreadFactory(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        if (StringUtils.isEmpty(threadName)) {
            thread.setName(r.getClass() + "-" + ID.getAndAdd(DELTA));
        } else {
            thread.setName(threadName + "-" + ID.getAndAdd(DELTA));
        }
        thread.setDaemon(false);
        return thread;
    }
}
