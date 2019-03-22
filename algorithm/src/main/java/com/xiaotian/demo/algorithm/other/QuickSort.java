package com.xiaotian.demo.algorithm.other;

import com.xiaotian.demo.algorithm.ArrayUtil;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        for (int i = 0; i < 10000; i++) {
            int[] array = ArrayUtil.generateByLength(i);
            quickSort.quickSort(array);
            if (!ArrayUtil.isAsc(array)) {
                throw new IllegalStateException("array is not sorted!");
            }
        }

    }

    public void quickSort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }

    private void quickSort(int[] numbers, int start, int end) {
        if (start < end) {
            int partition = partition(numbers, start, end);
            quickSort(numbers, start, partition);
            quickSort(numbers, partition + 1, end);
        }
    }

    private int partition(int[] numbers, int start, int end) {
        int selectValue = numbers[(start + end) / 2];
        int[] copyOfNumbers = Arrays.copyOfRange(numbers, start, end + 1);
        for (int number : copyOfNumbers) {
            if (number > selectValue) {
                numbers[end--] = number;
            } else if (number < selectValue) {
                numbers[start++] = number;
            }
        }
        while (start <= end) {
            numbers[end--] = selectValue;
        }
        return start;
    }
}
