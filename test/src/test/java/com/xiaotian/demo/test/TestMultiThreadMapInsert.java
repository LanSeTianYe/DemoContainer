package com.xiaotian.demo.test;

import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMultiThreadMapInsert {
    /**
     * 每次插入的数量
     */
    private static int count = 100;

    /**
     * 线程数量
     */
    private static final int threadCount = 8;


    private Map<Integer, Integer> hashTable = new Hashtable<>();
    private Map<Integer,Integer> hashMap = new HashMap<>();

    @Test
    public void add_synchronized() throws InterruptedException {
        int totalCount = count * threadCount;
        while (totalCount < 270400) {
            ExecutorService executorService = Executors.newFixedThreadPool(8);
            CountDownLatch countDownLatch = new CountDownLatch(threadCount);

            //多线程
            long startTime1 = System.nanoTime();
            for (int i = 0; i < threadCount; i++) {
                HashMap<Integer, Integer> tempHashMap = new HashMap<>();
                executorService.submit(() -> {
                    for (int j = 0; j < count; j++) {
                        tempHashMap.put(j, j);
                    }
                    hashTable.putAll(tempHashMap);
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();
            long costTime1 = System.nanoTime() - startTime1;

            //单线程
            long startTime2 = System.nanoTime();
            for (int i = 0; i < totalCount; i++) {
                hashMap.put(i, i);
            }
            long costTime2 = System.nanoTime() - startTime2;


            Assert.assertEquals(hashTable.size(), count);
            Assert.assertEquals(hashMap.size(), totalCount);
            System.out.println("totalCount: " + totalCount + " add_synchronized: " + costTime1 + " addNoSynchronized: " + costTime2 + " c1/c2: " + new DecimalFormat("0.00").format(costTime1 * 1.0 / costTime2));

            count = count + 100;
            totalCount = count * threadCount;
            hashTable.clear();
            hashMap.clear();
        }
    }
}
