package com.sun.xiaotian.demo.algorithm.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/non-decreasing-array/">是否可以变更一个元素使得数组变为可非递增数组[<=]<a/>
 * 思路，变更一个元素，当出现前一个比后一个大的时候此时可以把第一个变小或者把第二个变大，额外考虑开头和结尾即可。
 */
public class NonDecreasingArray_665 {

    public boolean checkPossibility(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] <= nums[i + 1]) {
                continue;
            } else {
//                int temp = nums[i];
//                //modify nums[i]
//                nums[i] = (i == 0) ? Integer.MIN_VALUE : nums[i - 1];
//                if (isNoDecrease(nums, i)) {
//                    return true;
//                }
//                //recover && modify[nums[i+1]]
//                nums[i] = temp;
//                nums[i + 1] = (i + 1 == nums.length - 1) ? Integer.MAX_VALUE : nums[i + 2];
//                return isNoDecrease(nums, i);
                int[] copy1 = Arrays.copyOf(nums, nums.length);
                int[] copy2 = Arrays.copyOf(nums, nums.length);
                copy1[i] = (i == 0) ? Integer.MIN_VALUE : nums[i - 1];
                copy2[i + 1] = (i + 1 == nums.length - 1) ? Integer.MAX_VALUE : nums[i + 2];
                return isNoDecrease(copy1, i) || isNoDecrease(copy2, i);
            }
        }
        return true;
    }

    private boolean isNoDecrease(int[] nums, int start) {
        for (int i = start; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NonDecreasingArray_665 nonDecreasingArray_665 = new NonDecreasingArray_665();
        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{4, 2, 3}));
        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{4, 2, 1}));
        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{3, 4, 2, 3}));
        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{-1, 4, 2, 3}));
        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{1, 2, 4, 5, 3}));
        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{1, 3, 4, 2, 5}));
    }
}
