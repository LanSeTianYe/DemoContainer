package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class A_18_4SumTest {

    private A_18_4Sum a_18_4Sum;

    @Before
    public void init() {
        this.a_18_4Sum = new A_18_4Sum();
    }

    @Test
    public void fourSum() {
        List<List<Integer>> result = a_18_4Sum.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        assertEquals(3, result.size());
        result = a_18_4Sum.fourSum(new int[]{1, 0, -1, 0}, 1);
        assertEquals(0, result.size());
        result = a_18_4Sum.fourSum(new int[]{1, 0, -1, 0}, 0);
        assertEquals(1, result.size());
        result = a_18_4Sum.fourSum(new int[]{-493, -470, -464, -453, -451, -446, -445, -407, -406, -393, -328, -312,
                -307, -303, -259, -253, -252, -243, -221, -193, -126, -126, -122, -117, -106, -105, -101, -71, -20,
                -12, 3, 4, 20, 20, 54, 84, 98, 111, 148, 149, 152, 171, 175, 176, 211, 218, 227, 331, 352, 389,
                410, 420, 448, 485}, 1057);
        assertEquals(9, result.size());
    }
}