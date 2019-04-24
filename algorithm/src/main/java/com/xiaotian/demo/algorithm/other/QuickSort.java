package com.xiaotian.demo.algorithm.other;

public class QuickSort {

    public void quickSort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }

    private void quickSort(int[] numbers, int left, int right) {
        if (left >= right) {
            return;
        }

        int selectValue = selectPointValue(numbers, left, right);

        int start = left;
        int end = right;
        for (; ; ) {
            while (numbers[++start] < selectValue) ;
            while (numbers[--end] > selectValue) ;
            if (start < end) {
                swap(numbers, start, end);
            } else {
                break;
            }
        }
        
        quickSort(numbers, left, start - 1);
        quickSort(numbers, end + 1, right);
    }

    private int selectPointValue(int[] numbers, int left, int right) {
        int center = (left + right) / 2;
        if (numbers[left] > numbers[center]) {
            swap(numbers, left, center);
        }
        if (numbers[center] > numbers[right]) {
            swap(numbers, right, center);
        }
        if (numbers[left] > numbers[center]) {
            swap(numbers, left, center);
        }
        return numbers[center];
    }

    private void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
