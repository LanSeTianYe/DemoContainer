package com.xiaotian.demo.test.thread.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工厂类
 *
 * @author sunfeilong [2020/8/25 15:43]
 */
public class ThreadPoolFactory {

    private static final Logger log = LoggerFactory.getLogger(ThreadPoolFactory.class);

    private static final int IO_TASK_COUNT = 10000;
    private static final int CPU_TASK_COUNT = 100000;

    /**
     * 获取IO密集型线程吃配置
     *
     * @return 线程池
     */
    public static ThreadPoolExecutor getIo(String name) {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxCorePoolSize = corePoolSize * 10;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(IO_TASK_COUNT);
        return new ThreadPoolExecutor(corePoolSize, maxCorePoolSize, 5, TimeUnit.SECONDS, queue, new DemoThreadFactory(""), (r, executor) -> {
            log.error("task cannot be execute, task:{}, executor:{}", r, executor);
        });
    }

    /**
     * 获取CPU密集型线程吃配置
     *
     * @return 线程池
     */
    public static ThreadPoolExecutor getCpu(String name) {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(CPU_TASK_COUNT);
        return new ThreadPoolExecutor(corePoolSize, corePoolSize, 5, TimeUnit.SECONDS, queue, new DemoThreadFactory(name), (r, executor) -> {
            log.error("task cannot be execute, task:{}, executor:{}", r, executor);
        });
    }
}
