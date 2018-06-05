package com.sun.xiaotian.demo.test.concurrent.c_future;


import com.sun.xiaotian.demo.test.util.TimeStatisticsUtil;

import java.util.concurrent.CompletableFuture;

public class TestThenCombine {

    public static void main(String[] args) {
        TimeStatisticsUtil.startTask("TestThenCombine");
        CompletableFuture<Pig> pig_1 = CompletableFuture.supplyAsync(() -> {
            Pig pig = new Pig("pig_1");
            pig.sleep();
            return pig;
        });

        CompletableFuture<Pig> pig_2 = CompletableFuture.supplyAsync(() -> {
            Pig pig2 = new Pig("pig_2");
            pig2.sleep();
            return pig2;
        });

        String result = pig_1.thenCombine(pig_2, (pig1, pig2) -> {
            System.out.println(pig1);
            System.out.println(pig2);
            return pig1 + ":" + pig2;
        }).join();

        TimeStatisticsUtil.endTask();

        System.out.println(result);
    }
}
