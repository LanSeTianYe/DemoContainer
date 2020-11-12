package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class A_1396_UndergroundSystemTest {

    private A_1396_UndergroundSystem undergroundSystem;

    @Before
    public void init() {
        undergroundSystem = new A_1396_UndergroundSystem();
    }

    @Test
    public void testEmpty() {
        double averageTime = undergroundSystem.getAverageTime("A", "B");
        Assert.assertEquals(0, averageTime, 0);
    }

    @Test
    public void testOne() {

        undergroundSystem.checkIn(1, "A", 1);
        undergroundSystem.checkOut(1, "B", 2);

        double averageTime = undergroundSystem.getAverageTime("A", "B");
        Assert.assertEquals(1, averageTime, 0);
    }
}
