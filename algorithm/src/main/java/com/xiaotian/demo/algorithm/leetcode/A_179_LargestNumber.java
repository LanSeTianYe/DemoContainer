package com.xiaotian.demo.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 思路:
 */
public class A_179_LargestNumber {

    public String largestNumber(int[] nums) {
        if (null == nums || nums.length == 0) return "";

        List<String> numbers = Arrays.stream(nums).mapToObj(String::valueOf).sorted((s1, s2) -> {
            String temp1 = s1 + s2;
            String temp2 = s2 + s1;
            return temp2.compareTo(temp1);
        }).collect(Collectors.toList());
        if (numbers.get(0).charAt(0) == '0') {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        numbers.forEach(result::append);
        return result.toString();
    }

    public String largestNumber_notUseStream(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        int len = nums.length;
        String[] strs = new String[len];
        for (int i = 0; i < len; i++)
            strs[i] = String.valueOf(nums[i]);

        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String s1, String s2) {
                String str1 = s1 + s2;
                String str2 = s2 + s1;
                return str2.compareTo(str1);
            }
        });

        if (strs[0].charAt(0) == '0') return "0";

        StringBuilder sb = new StringBuilder();
        for (String tmp : strs) sb.append(tmp);
        return sb.toString();
    }
}
