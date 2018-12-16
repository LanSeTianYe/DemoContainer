package com.sun.xiaotian.demo.test;

public class TestFindMax {

    public int findMax(int[] numbers) {
        return findMax(numbers, 0, numbers.length - 1);
    }


    private int findMax(int[] numbers, int start, int end) {
        if (start == end) {
            return numbers[start];
        }
        int center = (start + end) / 2;
        return Math.max(findMax(numbers, start, center), findMax(numbers, center + 1, end));
    }

    public static void main(String[] args) {
        TestFindMax findMax = new TestFindMax();
        System.out.println(findMax.findMax(new int[]{1}));
        System.out.println(findMax.findMax(new int[]{1, 1, 1, 2, 1, 1, 1, 1, 1, 1,}));
        System.out.println(findMax.findMax(new int[]{1, 2, 2}));
        System.out.println(findMax.findMax(new int[]{2, 1, 2}));
        System.out.println(findMax.findMax(new int[]{2, 1, 2,123,123,123,12,31,23,12,3,12,34,12,3,123,12,3,123,12,31,2312,23,545,67,2,231,6,2,32,6,213,42,542,34,234}));
        System.out.println(findMax.findMax(new int[]{2, 1, 2}));
    }
}
