package com.sun.xiaotian.demo.algorithm.leetcode;


/**
 * <a href="https://leetcode.com/problems/maximum-subarray/">最大序列和</a>
 */
public class MaximumSubarray_53 {

    //O(n)
    public int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxSum = Math.max(maxSum, nums[i - 1]);
            if (nums[i - 1] > 0) {
                nums[i] = nums[i - 1] + nums[i];
            }
            maxSum = Math.max(maxSum, nums[i]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSubarray_53 maximumSubarray_53 = new MaximumSubarray_53();
        System.out.println(maximumSubarray_53.maxSubArray(new int[]{-2}));
        System.out.println(maximumSubarray_53.maxSubArray(new int[]{-2}));
        System.out.println(maximumSubarray_53.maxSubArray(new int[]{-3, -4, -2}));
        System.out.println(maximumSubarray_53.maxSubArray(new int[]{1}));
        System.out.println(maximumSubarray_53.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4, 4, -2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
