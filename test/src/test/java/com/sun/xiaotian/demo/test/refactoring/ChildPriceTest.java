package com.sun.xiaotian.demo.test.refactoring;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ChildPriceTest {

    private ChildPrice childPrice;

    @Before
    public void init() {
        this.childPrice = new ChildPrice();
    }

    @Test
    public void testDayDentedLessThanZero() {
        assertEquals(1.5d, childPrice.getPrice(-1), 0);
    }

    @Test
    public void testDayDentedEqualsZero() {
        assertEquals(1.5d, childPrice.getPrice(0), 0);
    }

    @Test
    public void testDayDentedEqualsThree() {
        assertEquals(1.5d, childPrice.getPrice(3), 0);
    }

    @Test
    public void testDayDentedGraterThanThree() {
        assertEquals(3, childPrice.getPrice(4), 0);
    }
}