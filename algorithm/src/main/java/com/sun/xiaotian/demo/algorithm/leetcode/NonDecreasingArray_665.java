package com.sun.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/non-decreasing-array/">是否可以变更一个元素使得数组变为可非递增数组[<=]<a/>
 */
public class NonDecreasingArray_665 {

    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }

        boolean hasModify = false;
        if (nums[0] > nums[1]) {
            nums[0] = nums[1];
            hasModify = true;
        }

        if (nums[nums.length - 1] < nums[nums.length - 2]) {
            nums[nums.length - 1] = nums[nums.length - 2];
            if (hasModify) {
                return false;
            } else {
                hasModify = true;
            }
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                if (hasModify) {
                    return false;
                } else {
                    if (nums[i] > nums[i - 1]) {
                        nums[i] = Math.max(nums[i - 1], nums[i + 1]);
                    } else {
                        nums[i + 1] = nums[i];
                    }
                    if (nums[i + 1] < nums[i]) {
                        return false;
                    }
                    hasModify = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NonDecreasingArray_665 nonDecreasingArray_665 = new NonDecreasingArray_665();
//        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{4, 2, 3}));
//        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{4, 2, 1}));
//        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{3, 4, 2, 3}));
//        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{-1, 4, 2, 3}));
//        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{1, 2, 4, 5, 3}));
        System.out.println(nonDecreasingArray_665.checkPossibility(new int[]{1, 3, 4, 2, 5}));
    }
}
