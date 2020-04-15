package com.xiaotian.demo.test.concurrent.fork.sort;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SortTask extends RecursiveAction {

    private static final int MIN_LENGTH = 10;

    private final int[] array;
    private final int left;
    private final int right;

    public SortTask(int[] arrays) {
        this.array = arrays;
        this.left = 0;
        this.right = arrays.length;
    }

    private SortTask(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (needSplit(this.left, this.right)) {
            int mid = (this.right + this.left) / 2;
            invokeAll(new SortTask(this.array, this.left, mid), new SortTask(this.array, mid, this.right));
            merge(this.left, mid, this.right);
        } else {
            Arrays.sort(this.array, left, right);
        }
    }

    private boolean needSplit(int left, int right) {
        return right - left > MIN_LENGTH;
    }

    private void merge(int left, int mid, int right) {
        int[] temp = Arrays.copyOfRange(this.array, left, mid);
        for (int i = 0, j = mid, start = left; i < temp.length || j < right; start++) {
            if (i < temp.length && j < right) {
                if (temp[i] < this.array[j]) {
                    this.array[start] = temp[i];
                    i++;
                } else {
                    this.array[start] = this.array[j];
                    j++;
                }
            } else if (i >= temp.length) {
                this.array[start] = this.array[j];
                j++;
            } else {
                this.array[start] = temp[i];
                i++;
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] data = generateData(100000000);
        int[] data_copy = Arrays.copyOfRange(data, 0, data.length);
        long start = System.nanoTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Void> task = forkJoinPool.submit(new SortTask(data));
        task.get();
        long end = System.nanoTime();
        System.out.println("耗时:" + (end - start));

        start = System.nanoTime();
        Arrays.sort(data_copy);
        end = System.nanoTime();
        System.out.println("耗时:" + (end - start));


    }

    private static int[] generateData(int length) {
        return IntStream
                .range(0, length)
                .parallel()
                .map(i -> ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE))
                .toArray();
    }

}
