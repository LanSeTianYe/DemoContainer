package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_494_Target_SumTest {

    private A_494_Target_Sum a_494_target_sum;

    @Before
    public void setUp() throws Exception {
        a_494_target_sum = new A_494_Target_Sum();
    }

    @Test
    public void findTargetSumWays() {
        assertEquals(0, a_494_target_sum.findTargetSumWays(new int[]{}, 2));
        assertEquals(0, a_494_target_sum.findTargetSumWays(new int[]{1}, 2));
        assertEquals(2, a_494_target_sum.findTargetSumWays(new int[]{1, 0}, 1));
        assertEquals(5, a_494_target_sum.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        assertEquals(256, a_494_target_sum.findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 1));
    }
}