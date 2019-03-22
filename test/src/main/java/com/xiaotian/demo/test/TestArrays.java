package com.xiaotian.demo.test;

import java.util.Arrays;

public class TestArrays {

    public static void main(String[] args) {
        TestArrays testArrays = new TestArrays();
        testArrays.testCopyRange();
    }

    public void testCopyRange() {
        int[] numbers = new int[]{1};
        System.out.println("-------------------------------------------------------------------");
        Arrays.stream(Arrays.copyOfRange(numbers, 0, 0)).forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------");
        Arrays.stream(Arrays.copyOfRange(numbers, 0, 1)).forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------");
        Arrays.stream(Arrays.copyOfRange(numbers, 0, 2)).forEach(System.out::println);
    }
}
