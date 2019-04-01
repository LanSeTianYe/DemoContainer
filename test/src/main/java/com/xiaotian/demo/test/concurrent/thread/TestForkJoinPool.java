package com.xiaotian.demo.test.concurrent.thread;

import com.xiaotian.demo.test.util.TimeStatisticsUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TestForkJoinPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestForkJoinPool testForkJoinPool = new TestForkJoinPool();

        int[] array = testForkJoinPool.generateArray(10000);
        TimeStatisticsUtil.startTask("calculateSumByCumulative");
        int resultByCumulative = testForkJoinPool.calculateSumByCumulative(array);

        TimeStatisticsUtil.endTask();
        TimeStatisticsUtil.startTask("calculateSumByForkJoin");
        int resultByForkJoin = testForkJoinPool.calculateSumByForkJoin(array);
        TimeStatisticsUtil.endTask();

        TimeStatisticsUtil.showResult();

        System.out.println(resultByCumulative == resultByForkJoin);
    }


    public int calculateSumByCumulative(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum = sum + number;
            for (int i = 0; i < 1000000; i++) {
                sum = sum * 1 * 1;
            }
        }
        return sum;
    }

    public int calculateSumByForkJoin(int[] numbers) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> task = forkJoinPool.submit(new CountTask(numbers));
        return task.get();
    }

    private int[] generateArray(int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = i + 1;
        }
        return result;
    }
}

