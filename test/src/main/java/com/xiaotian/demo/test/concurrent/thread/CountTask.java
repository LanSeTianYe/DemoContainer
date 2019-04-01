package com.xiaotian.demo.test.concurrent.thread;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = -8192577574829580117L;

    /**
     * 开始计算的门槛
     */
    private final int THRESHOLD = 2;

    private final int[] numbers;

    private final int startIndex;
    private final int endIndex;

    public CountTask(int[] numbers) {
        this.numbers = numbers;
        this.startIndex = 0;
        this.endIndex = numbers.length -1;
    }

    private CountTask(int[] numbers, int startIndex, int endIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (lengthIsLessThanThreshold(startIndex, endIndex)) {
            for (int i = startIndex; i <= endIndex; i++) {
                result += numbers[i];
                for (int j = 0; j < 1000000; j++) {
                    result = result * 1 * 1;
                }
            }
            return result;
        } else {
            int mediaIndex = (startIndex + endIndex) / 2;
            CountTask leftCountTask = new CountTask(numbers, startIndex, mediaIndex);
            CountTask rightCountTask = new CountTask(numbers, mediaIndex + 1, endIndex);
            ForkJoinTask<Integer> leftResult = leftCountTask.fork();
            ForkJoinTask<Integer> rightResult = rightCountTask.fork();
            return leftResult.join() + rightResult.join();
        }
    }

    private boolean lengthIsLessThanThreshold(int startIndex, int endIndex) {
        return endIndex - startIndex <= THRESHOLD;
    }
}
