package com.xiaotian.demo.algorithm.leetcode;

public class A_494_Target_Sum {

    public int findTargetSumWays(int[] nums, int S) {
        int result = 0;
        if (null == nums || nums.length == 0) {
            return result;
        }
        int length = nums.length;
        int[] temp = new int[2 << (length + 1)];

        if (length == 1) {
            if (S == nums[0]) {
                result++;
            }
            if (S == -nums[0]) {
                result++;
            }
        } else {
            temp[0] = nums[0];
            temp[1] = -nums[0];
        }

        int preSumEndIndex = 2;
        for (int i = 1; i < length; i++) {
            int preSumLength = 1 << i;
            int nextIndex = preSumEndIndex;
            for (int j = preSumEndIndex - preSumLength; j < preSumEndIndex; j++) {
                if ((temp[nextIndex] = temp[j] + nums[i]) == S && i == length - 1) {
                    result++;
                }
                nextIndex++;
                if ((temp[nextIndex] = temp[j] - nums[i]) == S && i == length - 1) {
                    result++;
                }
                nextIndex++;
            }
            preSumEndIndex = nextIndex;
        }
        return result;
    }
}
