package com.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class A_438_FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() == 0 || p.length() == 0 || s.length() < p.length()) {
            return result;
        }

        int[] charCountInP = new int[26];
        // Integer.MIN_VALUE represent that the char is not appear in p
        for (int i = 0; i < charCountInP.length; i++) {
            charCountInP[i] = Integer.MIN_VALUE;
        }
        for (char c : p.toCharArray()) {
            charCountInP[c - 'a'] = Math.max(0, charCountInP[c - 'a']) + 1;
        }

        int sameNumber = 0;
        int addCharIndex;
        int removeCharIndex;
        char[] sArr = s.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            addCharIndex = sArr[i] - 'a';
            if (i >= p.length()) {
                removeCharIndex = sArr[i - p.length()] - 'a';
                if (charCountInP[removeCharIndex] != Integer.MIN_VALUE) {
                    charCountInP[removeCharIndex]++;
                    if (charCountInP[removeCharIndex] > 0) {
                        sameNumber--;
                    }
                }
            }
            if (charCountInP[addCharIndex] != Integer.MIN_VALUE) {
                charCountInP[addCharIndex]--;
            }
            if (charCountInP[addCharIndex] >= 0) {
                sameNumber++;
            }
            if (sameNumber == p.length()) {
                result.add(i - p.length() + 1);
            }
        }
        return result;
    }
}
