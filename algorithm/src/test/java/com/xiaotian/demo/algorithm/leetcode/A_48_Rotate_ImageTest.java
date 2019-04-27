package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.leetcode.util.MatrixUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class A_48_Rotate_ImageTest {

    private static A_48_Rotate_Image rotate_image;

    @BeforeClass
    public static void init() {
        rotate_image = new A_48_Rotate_Image();
    }

    @Test
    public void rotate() {
        int[][] matrixCopy = MatrixUtil.init2DMatrix(10);
        int[][] matrix = MatrixUtil.init2DMatrix(10);
        rotate_image.rotate(matrix);
        assertEquals(matrixCopy[9][0], matrix[0][0]);
        matrixCopy = MatrixUtil.init2DMatrix(1);
        matrix = MatrixUtil.init2DMatrix(1);
        assertEquals(matrixCopy[0][0], matrix[0][0]);
    }
}