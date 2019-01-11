package com.xiaotian.demo.test.message;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

    /**
     * 开始计算的门槛
     */
    private final int THRESHOLD = 2;

    private final static int[] numberArr = new int[100];

    static {
        for (int i = 1; i <= 100; i++) {
            numberArr[i - 1] = i;
        }
    }

    private final int startIndex;
    private final int endIndex;

    public CountTask(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        boolean canCompute = endIndex - startIndex <= THRESHOLD;
        if (canCompute) {
            for (int i = startIndex; i <= endIndex; i++) {
                result += numberArr[i];
            }
        } else {
            int mediaIndex = (startIndex + endIndex) / 2;
            CountTask leftCountTask = new CountTask(startIndex, mediaIndex);
            CountTask rightCountTask = new CountTask(mediaIndex + 1, endIndex);
            ForkJoinTask<Integer> leftResult = leftCountTask.fork();
            ForkJoinTask<Integer> rightResult = rightCountTask.fork();
            return leftResult.join() + rightResult.join();
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0, 99);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(countTask);
        System.out.println(submit.get());
    }
}
