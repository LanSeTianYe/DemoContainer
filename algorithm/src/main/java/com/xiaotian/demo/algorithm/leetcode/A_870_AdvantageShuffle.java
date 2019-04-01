package com.xiaotian.demo.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/submissions/detail/208196297/">Advantage Shuffle</a>
 * 思路：田忌赛马
 */
public class A_870_AdvantageShuffle {

    public int[] advantageCount(int[] A, int[] B) {
        int[] result = new int[B.length];
        Map<Integer, Stack<Integer>> valueAndIndex = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            Stack<Integer> stack = valueAndIndex.getOrDefault(B[i], new Stack<>());
            if (stack.empty()) {
                valueAndIndex.put(B[i], stack);
            }
            stack.push(i);
        }

        Arrays.sort(B);
        Arrays.sort(A);

        int start = 0;
        int end = A.length - 1;
        for (int i = B.length - 1; i >= 0; i--) {
            result[valueAndIndex.get(B[i]).pop()] = (B[i] < A[end]) ? A[end--] : A[start++];
        }
        return result;
    }
}
