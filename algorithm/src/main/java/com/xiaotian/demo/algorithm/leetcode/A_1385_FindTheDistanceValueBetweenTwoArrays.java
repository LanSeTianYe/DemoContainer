package com.xiaotian.demo.algorithm.leetcode;

public class A_1385_FindTheDistanceValueBetweenTwoArrays {

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int distance = 0;

        for (int a1 : arr1) {
            if (!existsSmallerDistance(a1, arr2, d)) {
                distance = distance + 1;
            }
        }
        return distance;
    }

    private boolean existsSmallerDistance(int a1, int[] arr2, int d) {
        for (int a2 : arr2) {
            if (Math.abs(a1 - a2) <= d) {
                return true;
            }
        }
        return false;
    }
}

