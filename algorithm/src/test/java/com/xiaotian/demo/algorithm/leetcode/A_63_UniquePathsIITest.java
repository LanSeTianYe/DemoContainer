package com.xiaotian.demo.algorithm.leetcode;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import static org.junit.Assert.*;

public class A_63_UniquePathsIITest {

    private static A_63_UniquePathsII a_63_uniquePathsII;

    @BeforeClass
    public static void init() {
        a_63_uniquePathsII = new A_63_UniquePathsII();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        a_63_uniquePathsII.uniquePathsWithObstacles(null);
        a_63_uniquePathsII.uniquePathsWithObstacles(new int[][]{});
    }

    @Test
    public void uniquePathsWithObstacles() {
        assertEquals(1, a_63_uniquePathsII.uniquePathsWithObstacles(new int[][]{{0}}));
        assertEquals(1, a_63_uniquePathsII.uniquePathsWithObstacles(new int[][]{{0, 0, 0}}));
        assertEquals(2, a_63_uniquePathsII.uniquePathsWithObstacles(new int[][]
                {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }
}