package com.xiaotian.demo.algorithm.leetcode;

public class FindPeakElement_162 {

    public int findPeakElement(int[] nums) {
        return findPeak(nums, 0, nums.length - 1);
    }

    private int findPeak(int[] nums, int start, int end) {
        if (start == end) {
            if (isPeak(nums, start)) {
                return start;
            } else {
                return -1;
            }
        }

        int center = (start + end) / 2;
        if (isPeak(nums, center)) {
            return center;
        }

        int leftPeak = findPeak(nums, start, center);
        if (leftPeak >= 0) {
            return leftPeak;
        }
        int rightPeak = findPeak(nums, center + 1, end);
        if (rightPeak >= 0) {
            return rightPeak;
        }
        return -1;
    }

    private boolean isPeak(int[] nums, int index) {
        int left = index == 0 ? Integer.MIN_VALUE : nums[index - 1];
        int right = index == nums.length - 1 ? Integer.MIN_VALUE : nums[index + 1];
        return nums[index] >= left && nums[index] >= right;
    }


    public static void main(String[] args) {
        FindPeakElement_162 findPeakElement_162 = new FindPeakElement_162();
        System.out.println(findPeakElement_162.findPeakElement(new int[]{1, 2, 3, 4, 3}));
        System.out.println(findPeakElement_162.findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(findPeakElement_162.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        System.out.println(findPeakElement_162.findPeakElement(new int[]{3, 2, 1}));
    }
}
