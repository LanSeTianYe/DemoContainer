package com.xiaotian.demo.algorithm.leetcode;

public class Solution {

    public int minFallingPathSum(int[][] A) {
        if (null == A || A.length == 0) {
            return 0;
        }
        int length = A.length;
        for (int row = 1; row < length; row++) {
            for (int col = 0; col < length; col++) {
                int lastRow = row - 1;
                if (col == 0) {
                    A[row][col] += Math.min(A[lastRow][col], A[lastRow][col + 1]);
                } else if (col == length - 1) {
                    A[row][col] += Math.min(A[lastRow][col - 1], A[lastRow][col]);
                } else {
                    A[row][col] += Math.min(Math.min(A[lastRow][col - 1], A[lastRow][col + 1]), A[lastRow][col]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            min = Math.min(min, A[length - 1][i]);
        }
        return min;
    }
}
