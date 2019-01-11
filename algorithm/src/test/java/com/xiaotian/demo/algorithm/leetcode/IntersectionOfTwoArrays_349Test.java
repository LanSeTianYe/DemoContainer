package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;



public class IntersectionOfTwoArrays_349Test {

    private IntersectionOfTwoArrays_349 intersectionOfTwoArrays_349 = new IntersectionOfTwoArrays_349();

    @Test
    public void intersection() throws Exception {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {1, 2, 2, 1};
        int[] nums3 = {2, 2};
        assertTrue(intersectionOfTwoArrays_349.intersection(nums1, nums2).length == 2);
        assertTrue(intersectionOfTwoArrays_349.intersection(nums1, nums3).length == 1);
    }

}