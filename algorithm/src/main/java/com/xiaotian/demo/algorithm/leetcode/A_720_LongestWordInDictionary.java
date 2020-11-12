package com.xiaotian.demo.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class A_720_LongestWordInDictionary {

    private Set<String> dictMap;

    public String longestWord(String[] words) {
        if (Objects.isNull(words) || words.length == 0) {
            return "";
        }
        sort(words);

        dictMap = new HashSet<>();
        for (String word : words) {
            dictMap.add(word);
        }

        for (String word : words) {
            if (checkWord(word, dictMap)) {
                return word;
            }
        }
        return "";
    }

    private boolean checkWord(String word, Set<String> wordDict) {
        int length = word.length();
        char[] chars = word.toCharArray();
        String subWord = "";
        for (int i = 0; i < length; i++) {
            subWord += chars[i];
            if (!wordDict.contains(subWord)) {
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
