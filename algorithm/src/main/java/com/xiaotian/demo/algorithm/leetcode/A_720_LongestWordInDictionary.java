package com.xiaotian.demo.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class A_720_LongestWordInDictionary {

    public String longestWord(String[] words) {
        if (Objects.isNull(words) || words.length == 0) {
            return "";
        }
        sort(words);
        Set<String> dictSet = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (checkWordPrefix(word, dictSet)) {
                return word;
            }
        }
        return "";
    }

    private boolean checkWordPrefix(String word, Set<String> dictSet) {
        int length = word.length();
        for (int i = 0; i < length; i++) {
            if (!dictSet.contains(word.substring(0, i + 1))) {
                return false;
            }
        }
        return true;
    }

    private void sort(String[] words) {
        Arrays.sort(words, (a, b) -> {
            if (a.length() < b.length()) {
                return 1;
            } else if (a.length() > b.length()) {
                return -1;
            }
            return a.compareTo(b);
        });
    }
}
