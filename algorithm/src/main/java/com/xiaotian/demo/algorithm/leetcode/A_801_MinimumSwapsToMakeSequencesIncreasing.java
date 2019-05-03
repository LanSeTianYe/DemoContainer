package com.xiaotian.demo.algorithm.leetcode;


//todo review
public class A_801_MinimumSwapsToMakeSequencesIncreasing {

    public int minSwap(int[] A, int[] B) {
        int length = A.length;

        int[] notSwap = new int[length];
        int[] swap = new int[length];
        notSwap[0] = 0;
        swap[0] = 1;

        for (int i = 1; i < length; i++) {
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                swap[i] = swap[i - 1] + 1;
                notSwap[i] = notSwap[i - 1];
            }
            if (A[i - 1] < B[i] && B[i - 1] < A[i - 1]) {
                notSwap[i] = Math.min(notSwap[2], notSwap[i - 1]);
                swap[i] = Math.min(notSwap[i - 1] + 1, swap[i - 1]);
            }
        }

        return Math.min(swap[length - 1], notSwap[length - 1]);
    }
}
