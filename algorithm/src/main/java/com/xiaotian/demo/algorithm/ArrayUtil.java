package com.xiaotian.demo.algorithm;

import java.util.Random;

public class ArrayUtil {

    private static final Random random = new Random(System.currentTimeMillis());

    public static int[] generateByLength(int length) {
        int[] result;
        if (length <= 0) {
            result = new int[0];
        } else {
            result = new int[length];
            for (int i = 0; i < length; i++) {
                result[i] = random.nextInt(length * 100);
            }

        }
        return result;
    }

    public static void printArray(int[] arr) {
        System.out.print("[");
        String split = "";
        for (int i = 0; i < arr.length; i++) {
            System.out.print(split + arr[i]);
            split = " ";
        }
        System.out.print("]");
        System.out.println();
    }

    public static boolean isAsc(int arr[]) {
        if (arr == null || arr.length < 2) {
            return true;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
