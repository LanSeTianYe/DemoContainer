package com.xiaotian.demo.algorithm.leetcode;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_801_MinimumSwapsToMakeSequencesIncreasingTest {

    private static A_801_MinimumSwapsToMakeSequencesIncreasing a_801;

    @BeforeClass
    public static void init() {
        a_801 = new A_801_MinimumSwapsToMakeSequencesIncreasing();
    }

    @Test
    public void minSwap() {
        assertEquals(0, a_801.minSwap(new int[]{1}, new int[]{1}));
        assertEquals(1, a_801.minSwap(new int[]{1, 3, 5, 4}, new int[]{1, 2, 3, 7}));
        assertEquals(2, a_801.minSwap(new int[]{1, 3, 5, 4, 5}, new int[]{1, 2, 3, 7, 8}));
        assertEquals(1, a_801.minSwap(new int[]{0, 3, 4, 9, 10}, new int[]{2, 3, 7, 5, 6}));
    }
}