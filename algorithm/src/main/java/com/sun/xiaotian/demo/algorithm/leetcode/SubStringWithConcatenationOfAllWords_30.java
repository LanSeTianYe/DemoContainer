package com.sun.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/substring-with-concatenation-of-all-words/"> Substring with Concatenation of All Words<a/>
 */

public class SubStringWithConcatenationOfAllWords_30 {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (0 == words.length || s.length() < (words.length * words[0].length())) {
            return result;
        }

        int wordLength = words[0].length();
        int templateLength = words.length * wordLength;

        Map<String, Integer> wordNumberMap = new HashMap<>();
        for (int i = 0; i <= s.length() - templateLength; i++) {
            boolean contains = true;
            initWordMap(words, wordNumberMap);
            for (int j = 0; j < words.length; j++) {
                int beginIndex = i + (j * wordLength);
                String substring = s.substring(beginIndex, beginIndex + wordLength);
                Integer number = wordNumberMap.get(substring);
                if (null == number || number <= 0) {
                    contains = false;
                    break;
                } else {
                    wordNumberMap.put(substring, number - 1);
                }
            }
            if (contains) {
                result.add(i);
            }
        }
        return result;
    }

    private void initWordMap(String[] words, Map<String, Integer> wordMap) {
        wordMap.clear();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
    }

    public static void main(String[] args) {
        SubStringWithConcatenationOfAllWords_30 subStr = new SubStringWithConcatenationOfAllWords_30();
        System.out.println(subStr.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(subStr.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
        System.out.println(subStr.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
    }
}

