package com.xiaotian.demo.algorithm.leetcode;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_279_PerfectSquaresTest {

    private static A_279_PerfectSquares a_279_perfectSquares;

    @BeforeClass
    public static void init() {
        a_279_perfectSquares = new A_279_PerfectSquares();
    }

    @Test
    public void numSquares() {
        assertEquals(1, a_279_perfectSquares.numSquares(1));
        assertEquals(2, a_279_perfectSquares.numSquares(2));
        assertEquals(3, a_279_perfectSquares.numSquares(3));
        assertEquals(1, a_279_perfectSquares.numSquares(4));
        assertEquals(2, a_279_perfectSquares.numSquares(5));
        assertEquals(3, a_279_perfectSquares.numSquares(12));
        assertEquals(2, a_279_perfectSquares.numSquares(13));
    }
}