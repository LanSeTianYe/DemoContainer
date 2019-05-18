package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_525_ContiguousArrayTest {

    private A_525_ContiguousArray a_525_contiguousArray;

    @Before
    public void setUp() {
        this.a_525_contiguousArray = new A_525_ContiguousArray();
    }

    @Test
    public void findMaxLength() {
        int result;
        result = a_525_contiguousArray.findMaxLength(new int[]{0, 1});
        assertEquals(2, result);

        result = a_525_contiguousArray.findMaxLength(new int[]{0, 1, 0});
        assertEquals(2, result);

        result = a_525_contiguousArray.findMaxLength(new int[]{0, 1, 0, 0, 1, 0, 1, 1});
        assertEquals(8, result);
    }
}