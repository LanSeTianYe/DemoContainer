package com.xiaotian.demo.algorithm.leetcode;

public class A_279_PerfectSquares {

    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        int times[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            times[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                times[i] = Math.min(times[i], 1 + times[i - j * j]);
            }
        }
        return times[n];
    }
}
