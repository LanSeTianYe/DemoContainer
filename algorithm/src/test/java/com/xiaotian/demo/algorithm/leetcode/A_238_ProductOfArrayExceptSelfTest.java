package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class A_238_ProductOfArrayExceptSelfTest {

    private A_238_ProductOfArrayExceptSelf a_238_productOfArrayExceptSelf;

    @Before
    public void init() {
        this.a_238_productOfArrayExceptSelf = new A_238_ProductOfArrayExceptSelf();

    }

    @Test
    public void productExceptSelf() {

        int[] actual;
        actual = a_238_productOfArrayExceptSelf.productExceptSelf(null);
        Assert.assertEquals(0, actual.length);

        actual = a_238_productOfArrayExceptSelf.productExceptSelf(new int[]{});
        Assert.assertEquals(0, actual.length);

        actual = a_238_productOfArrayExceptSelf.productExceptSelf(new int[]{1});
        Assert.assertEquals(1, actual.length);
        Assert.assertEquals(1, actual[0]);

        actual = a_238_productOfArrayExceptSelf.productExceptSelf(new int[]{1, 2, 3, 4});
        Assert.assertEquals(4, actual.length);
        Assert.assertArrayEquals(new int[]{24, 12, 8, 6}, actual);
    }
}