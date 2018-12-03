package com.sun.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 包含重复数字的排列组合问题
 * <a https://leetcode.com/problems/permutations-ii/"></>
 */
public class PermutationsII_47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Set<String> existsArray = new HashSet<String>();
        if (null == nums || nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        permute(nums, nums.length - 1, result, existsArray);
        return result;
    }


    private void permute(int[] nums, int n, List<List<Integer>> result, Set<String> existsArray) {
        if (n == 0) {
            String arrayStr = arryToStr(nums);
            if (!existsArray.contains(arrayStr)) {
                existsArray.add(arrayStr);
                List<Integer> temp = new ArrayList<Integer>();
                for (int num : nums) {
                    temp.add(num);
                }
                result.add(temp);
            }
        } else {
            for (int i = n; i >= 0; i--) {
                swampIAndJ(i, n, nums);
                permute(nums, n - 1, result, existsArray);
                swampIAndJ(n, i, nums);
            }
        }
    }

    private void swampIAndJ(int i, int j, int[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private String arryToStr(int[] nums) {
        StringBuilder result = new StringBuilder();
        for (int num : nums) {
            result.append(num);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        PermutationsII_47 permutationsII_47 = new PermutationsII_47();
        List<List<Integer>> result = permutationsII_47.permuteUnique(new int[]{1, 1, 3});
        System.out.println(result);
    }
}
