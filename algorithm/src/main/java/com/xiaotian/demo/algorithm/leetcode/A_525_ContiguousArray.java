package com.xiaotian.demo.algorithm.leetcode;

public class A_525_ContiguousArray {

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int result = 0;
        int length = nums.length;

        int[] leftOneNumber = new int[length + 1];
        int[] leftZeroNumber = new int[length + 1];
        leftOneNumber[0] = 0;
        leftZeroNumber[0] = 0;

        for (int i = 1; i < length + 1; i++) {
            leftOneNumber[i] = nums[i - 1] == 1 ? leftOneNumber[i - 1] + 1 : leftOneNumber[i - 1];
            leftZeroNumber[i] = nums[i - 1] == 0 ? leftZeroNumber[i - 1] + 1 : leftZeroNumber[i - 1];
        }

        for (int i = 0; i < leftOneNumber.length + 1; i++) {
            for (int j = length; j > i; j--) {
                if (j - i < result) {
                    break;
                }
                if (leftOneNumber[j] - leftOneNumber[i] == leftZeroNumber[j] - leftZeroNumber[i]) {
                    result = Math.max(result, j - i);
                }
            }
        }
        return result;
    }
}
