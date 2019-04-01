package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/peak-index-in-a-mountain-array/">二叉搜索<a/>
 */
public class A_704_BinarySearch {

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int center;
        while (start <= end) {
            center = (start + end) / 2;
            if (nums[center] == target) {
                return center;
            } else if (nums[center] < target) {
                start = center + 1;
            } else {
                end = center - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        A_704_BinarySearch binarySearch_704 = new A_704_BinarySearch();
        System.out.println(binarySearch_704.search(new int[]{1}, 10));
        System.out.println(binarySearch_704.search(new int[]{1, 3, 20}, 10));
        System.out.println(binarySearch_704.search(new int[]{1, 17, 19}, 10));
        System.out.println(binarySearch_704.search(new int[]{1, 17, 19}, 10));
        System.out.println(binarySearch_704.search(new int[]{1, 17, 19}, 10));
        System.out.println(binarySearch_704.search(new int[]{1, 3, 5, 10, 11, 19, 13213}, 10));

    }
}
