package com.xiaotian.demo.test.concurrent.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduled {

    public static void main(String[] args) {

        int corePoolSize = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(corePoolSize);

        System.out.println(String.format("start scheduleAtFixedRate: %s", System.currentTimeMillis() / 1000));

        //run next invoke after delay time when last invoke execute start
        scheduledExecutorService.scheduleAtFixedRate(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("scheduleAtFixedRate %s", System.currentTimeMillis() / 1000));
                },
                1,
                1,
                TimeUnit.SECONDS
        );

        //run next invoke after delay time when last invoke execute success
        System.out.println(String.format("start scheduleWithFixedDelay: %s", System.currentTimeMillis() / 1000));
        scheduledExecutorService.scheduleWithFixedDelay(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("scheduleWithFixedDelay %s", System.currentTimeMillis() / 1000));
                }
                ,
                1,
                2,
                TimeUnit.SECONDS
        );

    }
}
