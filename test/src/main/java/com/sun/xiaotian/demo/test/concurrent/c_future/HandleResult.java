package com.sun.xiaotian.demo.test.concurrent.c_future;

import java.util.concurrent.CompletableFuture;

public class HandleResult {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            Pig pig = new Pig("pig_1");
            pig.sleep();
            if(1 == 1) {
                throw new RuntimeException("");
            }
            return pig;
        }).handle((pig, throwable) -> {
            if (throwable != null) {
                return new Pig("error_pig");
            }
            return pig;
        }).thenAccept(System.err::println).join();
    }
}
