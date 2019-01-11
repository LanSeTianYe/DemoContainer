package com.xiaotian.demo.algorithm.leetcode;


import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/intersection-of-two-arrays/description/"></a>
 */

public class IntersectionOfTwoArrays_349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> tempResult = new HashSet<>();
        if (null == nums1 || null == nums2 || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        int[] maxArray;
        Set<Integer> miniArraySet = new HashSet<>();
        if(nums1.length >= nums2.length) {
            maxArray = nums1;
            for (int i : nums2) { miniArraySet.add(i); }
        } else {
            maxArray = nums2;
            for (int i : nums1) { miniArraySet.add(i); }
        }

        for (int num : maxArray) {
            if (miniArraySet.contains(num)) { tempResult.add(num); }
        }

        int[] result = new int[tempResult.size()];
        int index = 0;
        Iterator<Integer> iterator = tempResult.iterator();
        while (iterator.hasNext()) { result[index++] = iterator.next(); }
        return result;
    }
}
