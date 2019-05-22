package com.xiaotian.demo.algorithm.leetcode;

public class A_674_LongestContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int result = 1;
        int subLength = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                subLength++;
            } else {
                result = Math.max(result, subLength);
                subLength = 1;
            }
        }
        result = Math.max(result, subLength);
        return result;
    }
}
