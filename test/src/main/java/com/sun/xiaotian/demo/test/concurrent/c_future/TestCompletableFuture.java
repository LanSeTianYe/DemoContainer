package com.sun.xiaotian.demo.test.concurrent.c_future;


import com.sun.xiaotian.demo.test.util.TimeStatisticsUtil;

import java.util.concurrent.CompletableFuture;

public class TestCompletableFuture {

    public static void main(String[] args) throws InterruptedException {

        TimeStatisticsUtil.startTask("pig_a_sleep");

        CompletableFuture.supplyAsync(() -> {
            Pig pig_a = new Pig("pig_a");
            pig_a.sleep();
            return pig_a;
        }).whenComplete(((pig, throwable) -> {
            if (null != throwable) {
                throwable.printStackTrace();
            }
            System.out.println(pig);
        })).join();

        TimeStatisticsUtil.endTask();
        TimeStatisticsUtil.showResult();
    }
}
