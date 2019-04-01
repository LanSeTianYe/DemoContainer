package com.xiaotian.demo.algorithm.leetcode;

/**
 * https://leetcode.com/problems/counting-bits/
 */

public class A_338_CountingBits {

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        if (num >= 0) {
            result[0] = 0;
        }
        if (num >= 1) {
            result[1] = 1;
        }

        int start = 0;
        int end = 1;
        for (int i = 2; i <= num; i++) {
            result[i] = 1 + result[start];
            if (start == end) {
                start = 0;
                end = i;
            } else {
                start++;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        A_338_CountingBits countingBits = new A_338_CountingBits();
        System.out.println(countingBits.countBits(5));
    }
}
