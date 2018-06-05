package com.sun.xiaotian.demo.test.concurrent.c_future;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class ListCompletableFuture {

    private static final AtomicInteger pigNum = new AtomicInteger(0);

    private static final int count = 10;


    public static void main(String[] args) throws InterruptedException {
        ListCompletableFuture listCompletableFuture = new ListCompletableFuture();
        List<CompletableFuture<Pig>> completableFutures = listCompletableFuture.initCompletableFutureList(count);

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()])).whenComplete((pig, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
            }
        }).thenRunAsync(ListCompletableFuture::producePig).join();
//        TimeUnit.SECONDS.sleep(10);
    }


    private List<CompletableFuture<Pig>> initCompletableFutureList(int count) {
        if (count <= 0) {
            return Collections.emptyList();
        }
        List<CompletableFuture<Pig>> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(CompletableFuture.supplyAsync(ListCompletableFuture::producePig));
        }
        return result;
    }


    static Pig producePig() {
        Pig pig = new Pig("pig_" + pigNum.incrementAndGet());
        pig.sleep();
        return pig;
    }


}
