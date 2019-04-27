package com.xiaotian.demo.algorithm.leetcode.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixUtilTest {

    @Test
    public void initMatrix() {
        int[][] matrix = MatrixUtil.init2DMatrix(100);
        assertNotNull(matrix);
        assertNotNull(matrix[0]);
        assertEquals(100, matrix[0].length);
        assertEquals(100, matrix.length);
    }
}