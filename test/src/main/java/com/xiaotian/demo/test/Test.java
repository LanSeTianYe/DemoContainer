package com.xiaotian.demo.test;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 0};

        System.out.println(Arrays.toString(arr));

        Arrays.parallelPrefix(arr, (last, curr) -> last + curr);
        System.out.println(Arrays.toString(arr));

        int result = Arrays.stream(arr).parallel().reduce(5, (acc, e) -> acc * e);
        System.out.println(result);

    }
}
