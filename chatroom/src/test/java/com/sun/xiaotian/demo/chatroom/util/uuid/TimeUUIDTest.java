package com.sun.xiaotian.demo.chatroom.util.uuid;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class TimeUUIDTest {

    private static final Set<String> idSet = new HashSet<>();

    @Test
    public void get() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(() -> {
                while (true) {
                    System.out.println(TimeUUID.get());
                }
            });
        }
        TimeUnit.SECONDS.sleep(10);
        System.out.println(idSet.size());
    }
}