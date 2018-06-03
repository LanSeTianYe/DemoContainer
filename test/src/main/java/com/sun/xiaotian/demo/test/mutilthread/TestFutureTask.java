package com.sun.xiaotian.demo.test.mutilthread;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TestFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            System.out.println(String.format("Thread-Id: %s", Thread.currentThread().getId()));
            TimeUnit.SECONDS.sleep(1);
            return Thread.currentThread().getName();
        });

        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            System.out.println(String.format("Thread-Id: %s", Thread.currentThread().getId()));
            TimeUnit.SECONDS.sleep(2);
            return Thread.currentThread().getName();
        });

        futureTask1.run();
        System.out.println(futureTask1.get());
        
        futureTask2.run();
        System.out.println(futureTask2.get());
    }
}
