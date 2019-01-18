package com.xiaotian.demo.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 测试线程池
 */

public class TestThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(8);

        CountDownLatch forEachTimes = new CountDownLatch(1000);

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println("times:" + j);
                    forEachTimes.countDown();
                }
            });
        }

        forEachTimes.await();
        executorService.shutdownNow();

        System.out.println("Finished !");
    }
}
