package com.xiaotian.demo.algorithm.leetcode;

public class A_1385_FindTheDistanceValueBetweenTwoArrays {

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int distance = 0;

        for (int a1 : arr1) {
            boolean exists = false;
            for (int a2 : arr2) {
                if (Math.abs(a1 - a2) <= d) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                distance = distance + 1;
            }
        }
        return distance;
    }
}

