package com.sun.xiaotian.demo.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;


public class candy_135Test {

    candy_135 candy_135 = new candy_135();

    @Test
    public void testCandy() {
        assertTrue(5 == candy_135.candy(new int[]{1,0,2}));
        assertTrue(4 == candy_135.candy(new int[]{1,2,2}));
        assertTrue(7 == candy_135.candy(new int[]{1,3,2,2,1}));
        assertTrue(9 == candy_135.candy(new int[]{1,2,3,1,0}));
        assertTrue(60 == candy_135.candy(new int[]{1,2,3,3,3,2,1,0,-1,1,2,3,4,4,4,4,4,4,4,4,5,5,1,2,2,2,2,2,2,2,2,2,2,2}));
    }
}