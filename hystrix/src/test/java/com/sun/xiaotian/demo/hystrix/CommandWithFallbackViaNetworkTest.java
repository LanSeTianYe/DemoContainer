package com.sun.xiaotian.demo.hystrix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommandWithFallbackViaNetworkTest {

    private int times = 1000;
    private CountDownLatch countDownLatch = new CountDownLatch(times);
    private ExecutorService executorService = Executors.newFixedThreadPool(15);
    private final AtomicInteger counter = new AtomicInteger(0);

    private final static Logger logger = LogManager.getLogger(CommandWithFallbackViaNetworkTest.class);

    @Test
    public void testWithFallback() throws InterruptedException {
        for (int i = 0; i < times; i++) {
            executorService.submit(() -> {
                try {
                    int id = counter.addAndGet(1);
                    String executeResult = new CommandWithFallbackViaNetwork(id).execute();
                    logger.info(id + " : " + executeResult);
                    assertEquals("defaultValue", executeResult);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }
}