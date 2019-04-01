package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/palindromic-substrings/">Palindromic Substrings</a>
 */

public class A_647_PalindromicSubstrings {

    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count++;
            count += countPalindromic(s, i - 1, i + 1);
            count += countPalindromic(s, i, i + 1);
        }
        return count;
    }

    private int countPalindromic(String text, int start, int end) {
        int count = 0;
        while (start >= 0 && end < text.length() && text.charAt(start) == text.charAt(end)) {
            count++;
            start--;
            end++;
        }
        return count;
    }

    public static void main(String[] args) {
        A_647_PalindromicSubstrings substrings647 = new A_647_PalindromicSubstrings();
        System.out.println(substrings647.countSubstrings("a"));
        System.out.println(substrings647.countSubstrings("aaaa"));
        System.out.println(substrings647.countSubstrings("aaa"));
        System.out.println(substrings647.countSubstrings("abcba"));
    }
}
