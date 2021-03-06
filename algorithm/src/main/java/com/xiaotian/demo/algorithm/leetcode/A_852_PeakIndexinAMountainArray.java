package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/peak-index-in-a-mountain-array/">找到山峰数组的山峰<a/>
 */
public class A_852_PeakIndexinAMountainArray {

    public int peakIndexInMountainArray(int[] A) {
        int start = 0;
        int end = A.length;
        int center;
        do {
            center = (start + end) / 2;
            if (A[center - 1] < A[center] && A[center] > A[center + 1]) {
                return center;
            }
            if (A[center - 1] < A[center]) {
                start = center;
            } else {
                end = center;
            }
        } while (true);
    }

    public static void main(String[] args) {
        A_852_PeakIndexinAMountainArray peakIndexinAMountainArray_852 = new A_852_PeakIndexinAMountainArray();

        System.out.println(peakIndexinAMountainArray_852.peakIndexInMountainArray(new int[]{1, 3, 1}));
        System.out.println(peakIndexinAMountainArray_852.peakIndexInMountainArray(new int[]{1, 3, 5, 7, 9, 1}));
        System.out.println(peakIndexinAMountainArray_852.peakIndexInMountainArray(new int[]{1, 3, 5, 7, 9, 8, 1}));


    }
}
