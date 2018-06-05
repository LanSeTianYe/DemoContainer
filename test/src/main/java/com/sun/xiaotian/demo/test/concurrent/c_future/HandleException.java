package com.sun.xiaotian.demo.test.concurrent.c_future;

import java.util.concurrent.CompletableFuture;

public class HandleException {

    public static void main(String[] args) {
        CompletableFuture.<String>supplyAsync(() -> {
            throw new RuntimeException("出现异常了");
        }).exceptionally(throwable -> {
            System.err.println(throwable.getMessage());
            return "异常时的结果";
        }).thenAccept(System.out::println).join();
    }
}
