package com.xiaotian.demo.algorithm.leetcode;

public class A_48_Rotate_Image {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0] == null || (matrix.length != matrix[0].length)) {
            return;
        }
        int start = 0;
        int end = matrix.length - 1;
        while (end >= start) {
            int length = end - start + 1;
            for (int i = 0; i < length - 1; i++) {
                int temp = matrix[start][end - i];
                matrix[start][end - i] = matrix[start + i][start];
                matrix[start + i][start] = matrix[end][start + i];
                matrix[end][start + i] = matrix[end - i][end];
                matrix[end - i][end] = temp;
            }
            end--;
            start++;
        }
    }
}
