package com.xiaotian.demo.test.concurrent.c_future;


import com.xiaotian.demo.test.util.TimeStatisticsUtil;

import java.util.concurrent.CompletableFuture;

public class TestThenCompose {

    public static void main(String[] args) {
        TimeStatisticsUtil.startTask("TestThenCompose");
        CompletableFuture.supplyAsync(() -> {
            Pig pig = new Pig("pig_a");
            pig.sleep();
            return pig;
        }).thenCompose((pig -> {
            return CompletableFuture.supplyAsync(() -> {
                Pig pig2 = new Pig("pig_2");
                pig2.sleep();
                return pig2;
            });
        })).thenAccept(System.out::println).join();
        TimeStatisticsUtil.endTask();
    }
}
