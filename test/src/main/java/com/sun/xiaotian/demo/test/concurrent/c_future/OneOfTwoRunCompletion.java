package com.sun.xiaotian.demo.test.concurrent.c_future;

import java.util.concurrent.CompletableFuture;

/**
 * 获取一个的执行结果
 */
public class OneOfTwoRunCompletion {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<Pig> pig_1 = CompletableFuture.supplyAsync(() -> {
            Pig pig = new Pig("pig_1");
            pig.sleep();
            return pig;
        });

        CompletableFuture<Pig> pig_2 = CompletableFuture.supplyAsync(() -> {
            Pig pig = new Pig("pig_2");
            pig.sleep();
            return pig;
        });

        pig_1.acceptEither(pig_2, System.err::println).join();
    }
}
