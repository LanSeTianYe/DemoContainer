package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_983_MinimumCostForTicketsTest {

    private A_983_MinimumCostForTickets minimumCostForTickets;

    @Before
    public void setUp() throws Exception {
        minimumCostForTickets = new A_983_MinimumCostForTickets();
    }

    @Test
    public void mincostTickets() {
        assertEquals(
                0, minimumCostForTickets.mincostTickets(
                        new int[]{1},
                        new int[]{0, 38, 134}
                ));
        assertEquals(
                423, minimumCostForTickets.mincostTickets(
                        new int[]{1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 21, 24, 25, 27, 28, 29, 30, 31, 34, 37, 38, 39, 41, 43, 44, 45, 47, 48, 49, 54, 57, 60, 62, 63, 66, 69, 70, 72, 74, 76, 78, 80, 81, 82, 83, 84, 85, 88, 89, 91, 93, 94, 97, 99},
                        new int[]{9, 38, 134}
                ));
    }
}