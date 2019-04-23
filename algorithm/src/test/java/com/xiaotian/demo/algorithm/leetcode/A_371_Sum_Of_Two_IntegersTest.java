package com.xiaotian.demo.algorithm.leetcode;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_371_Sum_Of_Two_IntegersTest {

    private static A_371_Sum_Of_Two_Integers sum_of_two_integers;

    @BeforeClass
    public static void before() {
        sum_of_two_integers = new A_371_Sum_Of_Two_Integers();
    }

    @Test
    public void getSum() {
        assertEquals(5, sum_of_two_integers.getSum(2, 3));
        assertEquals(1, sum_of_two_integers.getSum(-2, 3));
        assertEquals(-4, sum_of_two_integers.getSum(-2, -2));
        assertEquals(-2, sum_of_two_integers.getSum(-2, 0));
        assertEquals(0, sum_of_two_integers.getSum(-2, 2));
    }
}