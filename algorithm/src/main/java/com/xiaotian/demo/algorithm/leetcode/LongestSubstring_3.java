package com.xiaotian.demo.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring_3 {

    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.equals("")) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> tempSet = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                if (tempSet.contains(s.charAt(j))) {
                    break;
                } else {
                    tempSet.add(s.charAt(j));
                }
            }
            result = Math.max(result, tempSet.size());
        }
        return result;
    }


    public static void main(String[] args) {
        LongestSubstring_3 longestSubstring = new LongestSubstring_3();
        System.out.println(longestSubstring.lengthOfLongestSubstring("aaa") == 1);
        System.out.println(longestSubstring.lengthOfLongestSubstring("") == 0);
        System.out.println(longestSubstring.lengthOfLongestSubstring("   ") == 1);
        System.out.println(longestSubstring.lengthOfLongestSubstring("   abc") == 4);
        System.out.println(longestSubstring.lengthOfLongestSubstring("   abcabcA") == 4);
        System.out.println(longestSubstring.lengthOfLongestSubstring("pwwkew") == 3);
    }

}
