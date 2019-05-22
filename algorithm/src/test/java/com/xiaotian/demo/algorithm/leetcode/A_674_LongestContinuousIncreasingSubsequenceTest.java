package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class A_674_LongestContinuousIncreasingSubsequenceTest {

    private A_674_LongestContinuousIncreasingSubsequence a_674;

    @Before
    public void setUp() {
        this.a_674 = new A_674_LongestContinuousIncreasingSubsequence();
    }

    @Test
    public void findLengthOfLCIS() {
        assertEquals(0, a_674.findLengthOfLCIS(null));
        assertEquals(0, a_674.findLengthOfLCIS(new int[]{}));
        assertEquals(1, a_674.findLengthOfLCIS(new int[]{1}));
        assertEquals(1, a_674.findLengthOfLCIS(new int[]{1, 1}));
        assertEquals(5, a_674.findLengthOfLCIS(new int[]{1, 2, 3, 4, 5}));
        assertEquals(4, a_674.findLengthOfLCIS(new int[]{1, 2, 3, 6, 5}));
        assertEquals(1, a_674.findLengthOfLCIS(new int[]{4, 3, 2, 1, 0}));
    }
}