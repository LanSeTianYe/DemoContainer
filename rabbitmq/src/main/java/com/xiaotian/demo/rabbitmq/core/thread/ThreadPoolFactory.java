package com.xiaotian.demo.rabbitmq.core.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工厂类
 */


public class ThreadPoolFactory {

    private static final int IO_TASK_COUNT = 100000;
    private static final int CPU_TASK_COUNT = 100000;

    /**
     * 获取IO密集型线程吃配置
     *
     * @return 线程池
     */
    public static ThreadPoolExecutor getIo(String threadName) {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxCorePoolSize = corePoolSize * 10;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(IO_TASK_COUNT);
        return new ThreadPoolExecutor(corePoolSize, maxCorePoolSize, 5, TimeUnit.SECONDS, queue, new RabbitThreadFactory(threadName), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 获取CPU密集型线程吃配置
     *
     * @return 线程池
     */
    public static ThreadPoolExecutor getCpu(String threadName) {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(CPU_TASK_COUNT);
        return new ThreadPoolExecutor(corePoolSize, corePoolSize, 5, TimeUnit.SECONDS, queue, new RabbitThreadFactory(threadName), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
