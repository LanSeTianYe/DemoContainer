package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_38_CountAndSayTest {

    private A_38_CountAndSay countAndSay;

    @Before
    public void setUp() {
        this.countAndSay = new A_38_CountAndSay();
    }

    @Test
    public void countAndSay() {
        assertEquals("1", countAndSay.countAndSay(1));
        assertEquals("11", countAndSay.countAndSay(2));
        assertEquals("21", countAndSay.countAndSay(3));
        assertEquals("1211", countAndSay.countAndSay(4));
        assertEquals("111221", countAndSay.countAndSay(5));
        assertEquals("312211", countAndSay.countAndSay(6));
        assertEquals("13112221", countAndSay.countAndSay(7));
    }

}