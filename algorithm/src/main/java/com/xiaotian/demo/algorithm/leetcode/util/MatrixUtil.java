package com.xiaotian.demo.algorithm.leetcode.util;

public class MatrixUtil {

    public static int[][] init2DMatrix(int length) {
        if (0 == length) {
            return new int[0][0];
        }
        int[][] matrix = new int[length][length];
        int number = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = number++;
            }
        }
        return matrix;
    }
}
