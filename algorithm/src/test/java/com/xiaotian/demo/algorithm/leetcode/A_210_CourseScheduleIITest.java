package com.xiaotian.demo.algorithm.leetcode;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_210_CourseScheduleIITest {

    private static A_210_CourseScheduleII a_210_courseScheduleII;

    @BeforeClass
    public static void init() {
        a_210_courseScheduleII = new A_210_CourseScheduleII();
    }

    @Test
    public void findOrder() {
        int[] order = a_210_courseScheduleII.findOrder(1, null);
        assertEquals(0, order[0]);
        order = a_210_courseScheduleII.findOrder(2, new int[][]{{1, 0}});
        assertEquals(0, order[0]);
        assertEquals(1, order[1]);
        order = a_210_courseScheduleII.findOrder(2, new int[][]{{1, 0}, {0, 1}});
        assertEquals(0, order.length);
        order = a_210_courseScheduleII.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        assertEquals(0, order[0]);
        assertEquals(3, order[3]);
    }
}