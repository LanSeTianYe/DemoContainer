package com.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class A_438_FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() == 0 || p.length() == 0 || s.length() < p.length()) {
            return result;
        }

        int[] target = new int[26];
        for (int i = 0; i < target.length; i++) {
            target[i] = Integer.MIN_VALUE;
        }
        for (char c : p.toCharArray()) {
            target[c - 'a'] = Math.max(0, target[c - 'a']) + 1;
        }

        int sameNumber = 0;
        int addCharIndex = 0;
        int removeCharIndex = 0;
        char[] sArr = s.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            addCharIndex = sArr[i] - 'a';
            if (i >= p.length()) {
                removeCharIndex = sArr[i - p.length()] - 'a';
                if (target[removeCharIndex] != Integer.MIN_VALUE) {
                    target[removeCharIndex]++;
                    if (target[removeCharIndex] > 0) {
                        sameNumber--;
                    }
                }
            }
            if (target[addCharIndex] != Integer.MIN_VALUE) {
                target[addCharIndex]--;
            }
            if (target[addCharIndex] >= 0) {
                sameNumber++;
            }
            if (sameNumber == p.length()) {
                result.add(i - p.length() + 1);
            }
        }
        return result;
    }
}
