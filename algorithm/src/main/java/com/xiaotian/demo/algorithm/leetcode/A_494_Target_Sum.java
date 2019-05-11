package com.xiaotian.demo.algorithm.leetcode;

public class A_494_Target_Sum {

    public int findTargetSumWays(int[] nums, int S) {
        if (null == nums) {
            return 0;
        }

        int maxSum = 0;
        for (int num : nums) maxSum += num;
        if (maxSum < Math.abs(S)) {
            return 0;
        }

        int allSituation = 2 * maxSum + 1;
        int dp[] = new int[allSituation];
        dp[maxSum + nums[0]] += 1;
        dp[maxSum - nums[0]] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] temp = new int[allSituation];
            for (int j = 0; j < allSituation; j++) {
                if (dp[j] != 0) {
                    temp[j + nums[i]] += dp[j];
                    temp[j - nums[i]] += dp[j];
                }
            }
            dp = temp;
        }
        return dp[maxSum + S];
    }
}
