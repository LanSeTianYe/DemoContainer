package com.sun.xiaotian.demo.test.concurrent.c_future;


import com.sun.xiaotian.demo.test.util.TimeStatisticsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAllOf {

    public static void main(String[] args) throws InterruptedException {

        List<CompletableFuture> completableFutures = new ArrayList<>();

        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            completableFutures.add(CompletableFuture.supplyAsync(() -> {
                Pig pig = new Pig("pig_" + count.incrementAndGet());
                pig.sleep();
                return pig;
            }));
        }

        TimeStatisticsUtil.startTask("allOf");
        CompletableFuture
                .allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]))
                .whenComplete((aVoid, throwable) -> {
                    if (null != throwable) {
                        throwable.printStackTrace();
                    }
                })
                .join();
        TimeStatisticsUtil.endTask();

        TimeStatisticsUtil.startTask("anyOf");
        CompletableFuture.anyOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()])).whenComplete((o, throwable) -> {
            if (null != throwable) {
                throwable.printStackTrace();
            }
            System.out.println(o);
        }).join();

        TimeStatisticsUtil.endTask();
        TimeStatisticsUtil.showResult();
    }
}
