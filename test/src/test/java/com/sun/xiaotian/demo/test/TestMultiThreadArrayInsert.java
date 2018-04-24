package com.sun.xiaotian.demo.test;


import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMultiThreadArrayInsert {
    /**
     * 每次插入的数量
     */
    private static int count = 100;

    /**
     * 线程数量
     */
    private static final int threadCount = 8;


    private List<Integer> tempArr_sync_LinkedList = Collections.synchronizedList(new LinkedList<>());
    private List<Integer> tempArr_NoSync_LinkedList = new LinkedList<>();

    @Test
    public void add_synchronized() throws InterruptedException {
        int totalCount = count * threadCount;
        while (totalCount < 270400) {
            ExecutorService executorService = Executors.newFixedThreadPool(8);
            CountDownLatch countDownLatch = new CountDownLatch(threadCount);

            //多线程
            long startTime1 = System.nanoTime();
            for (int i = 0; i < threadCount; i++) {
                executorService.submit(() -> {
                    for (int j = 0; j < count; j++) {
                        tempArr_sync_LinkedList.add(j);
                    }
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();
            long costTime1 = System.nanoTime() - startTime1;

            //单线程
            long startTime2 = System.nanoTime();
            for (int i = 0; i < totalCount; i++) {
                tempArr_NoSync_LinkedList.add(i);
            }
            long costTime2 = System.nanoTime() - startTime2;


            Assert.assertEquals(tempArr_sync_LinkedList.size(), totalCount);
            Assert.assertEquals(tempArr_NoSync_LinkedList.size(), totalCount);
            System.out.println("totalCount: " + totalCount + " add_synchronized: " + costTime1 + " addNoSynchronized: " + costTime2 + " c1/c2: " + new DecimalFormat("0.00").format(costTime1 * 1.0 / costTime2));

            count = count + 100;
            totalCount = count * threadCount;
            tempArr_NoSync_LinkedList.clear();
            tempArr_sync_LinkedList.clear();
        }
    }
}
