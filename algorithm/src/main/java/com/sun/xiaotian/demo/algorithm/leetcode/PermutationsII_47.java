package com.sun.xiaotian.demo.algorithm.leetcode;

import java.util.*;

/**
 * 包含重复数字的排列组合问题
 * <a https://leetcode.com/problems/permutations-ii/"></>
 */
public class PermutationsII_47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            result.add(Collections.emptyList());
            return result;
        }
        BitSet hasUseFlag = new BitSet();

        for (int n1 : nums) {
            if (hasUseFlag.get(n1)) {
                continue;
            }
            ArrayList<Integer> array = new ArrayList<>();
            for (int n2 : nums) {
            }
            hasUseFlag.set(n1);
        }


        return null;
    }

    public static void main(String[] args) {
        PermutationsII_47 permutationsII_47 = new PermutationsII_47();
        System.out.println(permutationsII_47.permuteUnique(null));
    }
}
