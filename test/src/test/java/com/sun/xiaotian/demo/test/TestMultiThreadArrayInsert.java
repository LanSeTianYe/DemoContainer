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
import java.util.concurrent.TimeUnit;

public class TestMultiThreadArrayInsert {
    /**
     * 每次插入的数量
     */
    private static int count = 100;

    /**
     * 线程数量
     */
    private static final int threadCount = 8;

    private ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

    private List<Integer> tempArr_sync_LinkedList = Collections.synchronizedList(new LinkedList<>());
    private List<Integer> tempArr_NoSync_LinkedList = new LinkedList<>();

    @Test
    public void add_synchronized() throws InterruptedException {
        int totalCount = count * threadCount;

        while (totalCount < 270400) {
            CountDownLatch countDownLatch = new CountDownLatch(threadCount);

            //多线程
            long startTime1 = System.nanoTime();
            for (int i = 0; i < threadCount; i++) {
                List<Integer> tempList = new LinkedList<>();
                executorService.submit(() -> {
                    for (int j = 0; j < count; j++) {
                        tempList.add(j);
                    }
                    tempArr_sync_LinkedList.addAll(tempList);
                    networkConnect();
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();
            long costTime1 = System.nanoTime() - startTime1;

            //单线程
            long startTime2 = System.nanoTime();
            for (int i = 0; i < totalCount; i++) {
                tempArr_NoSync_LinkedList.add(i);
                networkConnect();
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

    private void networkConnect() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
