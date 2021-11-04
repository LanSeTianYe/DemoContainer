package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class A_1385_FindTheDistanceValueBetweenTwoArraysTest {

    @Test
    public void findTheDistanceValue() {
        A_1385_FindTheDistanceValueBetweenTwoArrays a1385 = new A_1385_FindTheDistanceValueBetweenTwoArrays();
        int[] arr1 = new int[]{1, 4, 2, 3};
        int[] arr2 = new int[]{-4, -3, 6, 10, 20, 30};
        int d = 3;
        int value = a1385.findTheDistanceValue(arr1, arr2, d);
        Assert.assertEquals(2, value);

        arr1 = new int[]{2, 1, 100, 3};
        arr2 = new int[]{-5, -2, 10, -3, 7};
        d = 6;
        value = a1385.findTheDistanceValue(arr1, arr2, d);
        Assert.assertEquals(1, value);
    }
}
