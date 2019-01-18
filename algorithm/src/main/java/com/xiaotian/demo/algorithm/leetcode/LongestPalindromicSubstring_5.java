package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/longest-palindromic-substring/>最长回文串</a>
 * 假定每一个元素都可以成为回文的中心，存在两种情况：
 * 1. 和相邻节点相等，共同组成中心。
 * 2. 左右节点相等，当前节点为中心。
 */
public class LongestPalindromicSubstring_5 {

    public String longestPalindrome(String s) {
        String maxLengthPalindrome = "";
        char[] strArr = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            String result = getMaxLengthPalindrome(s, strArr, i);
            if (maxLengthPalindrome.length() < result.length()) {
                maxLengthPalindrome = result;
            }
        }
        return maxLengthPalindrome;
    }

    public String getMaxLengthPalindrome(String s, char[] sArr, int center) {
        String maxLengthPalindrome = "";
        //作为中点
        if (center >= 0 && center <= s.length()) {
            String result = findMaxPalindrome(s, sArr, center, center);
            //查找最大回文
            if (maxLengthPalindrome.length() < result.length()) {
                maxLengthPalindrome = result;
            }
        }

        //作为共同的中心
        if (center + 1 < s.length() && s.charAt(center + 1) == s.charAt(center)) {
            String result = findMaxPalindrome(s, sArr, center, center + 1);
            if (maxLengthPalindrome.length() < result.length()) {
                maxLengthPalindrome = result;
            }
        }
        return maxLengthPalindrome;
    }

    private String findMaxPalindrome(String s, char[] sArr, int left, int right) {
        //查找最大回文
        while (left - 1 >= 0 && right + 1 < s.length() && sArr[left - 1] == sArr[right + 1]) {
            left = left - 1;
            right = right + 1;
        }
        return s.substring(left, right + 1);
    }


    public static void main(String[] args) {
        LongestPalindromicSubstring_5 longestPalindromicSubstring_5 = new LongestPalindromicSubstring_5();
        System.out.println(longestPalindromicSubstring_5.longestPalindrome("abaaba"));
        System.out.println(longestPalindromicSubstring_5.longestPalindrome("abc"));
        System.out.println(longestPalindromicSubstring_5.longestPalindrome("babad"));
        System.out.println(longestPalindromicSubstring_5.longestPalindrome("ccb"));
        System.out.println(longestPalindromicSubstring_5.longestPalindrome("abcba"));
        System.out.println(longestPalindromicSubstring_5.longestPalindrome("a"));
        System.out.println(longestPalindromicSubstring_5.longestPalindrome("aa"));
        System.out.println(longestPalindromicSubstring_5.longestPalindrome("aaaa"));
        System.out.println(longestPalindromicSubstring_5.longestPalindrome(""));
        System.out.println(longestPalindromicSubstring_5.longestPalindrome("ddcd"));
        long start = System.currentTimeMillis();
        System.out.println(longestPalindromicSubstring_5.longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        System.out.println(System.currentTimeMillis() - start);
    }
}
