package com.sun.xiaotian.demo.test.concurrent.c_future;


import com.sun.xiaotian.demo.test.util.TimeStatisticsUtil;

import java.util.concurrent.CompletableFuture;

public class TestThenApply {

    public static void main(String[] args) {
        TimeStatisticsUtil.startTask("TestThenApply");

        CompletableFuture<Pig> pig_1 = CompletableFuture.supplyAsync(() -> {
            Pig pig = new Pig("pig_1");
            pig.sleep();
            return pig;
        });

        String join = pig_1.thenApply(pig -> {
            pig.sleep();
            return pig.toString();
        }).join();

        System.out.println(join);
        TimeStatisticsUtil.endTask();

    }
}
