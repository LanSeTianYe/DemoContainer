package com.sun.xiaotian.demo.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestTestRule {

    private Random random = new Random();

    @Rule
    public TestRule testRule = (Statement base, Description description) -> {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                int count = 1000;
                CountDownLatch countDownLatch = new CountDownLatch(count);
                System.out.println("before run\uD83D\uDE31:" + description);
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < count; i++) {
                    executorService.submit(() -> {
                        try {
                            base.evaluate();
                            countDownLatch.countDown();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    });
                }
                countDownLatch.await();
                long endTime = System.currentTimeMillis();
                System.out.println(description + ": ");
                System.out.println("cost time:\uD83D\uDE31 " + (endTime - startTime) + " ms");
                System.out.println("after run:\uD83D\uDE31" + description);
            }
        };
    };


    @Test
    public void testMethodA() throws InterruptedException {
        TimeUnit.NANOSECONDS.sleep(random.nextInt(1000));
    }

    @Test
    public void testMethodB() throws InterruptedException {
        TimeUnit.NANOSECONDS.sleep(random.nextInt(1000));
    }
}
