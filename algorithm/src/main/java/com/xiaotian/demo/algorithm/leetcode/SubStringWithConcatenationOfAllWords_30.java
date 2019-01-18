package com.xiaotian.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/substring-with-concatenation-of-all-words/"> Substring with Concatenation of All Words<a/>
 * 思路：用Map记录每个单词出现的次数，然后从字符串开始截取每个窗口，判断每个窗口切分出来的字符串是否和给定的单词出现的次数相同。
 */

public class SubStringWithConcatenationOfAllWords_30 {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (0 == words.length || s.length() < (words.length * words[0].length())) {
            return result;
        }

        int wordLength = words[0].length();
        int templateLength = words.length * wordLength;

        Map<String, Integer> wordNumberMap = initWordMap(words);
        Map<String, Integer> wordNumberCopy = null;
        for (int i = 0; i <= s.length() - templateLength; i++) {
            boolean contains = true;
            wordNumberCopy = new HashMap<>(wordNumberMap);
            for (int j = 0; j < words.length; j++) {
                String substring = s.substring(i + (j * wordLength), i + (j * wordLength) + wordLength);
                Integer number = wordNumberCopy.get(substring);
                if (null == number || number <= 0) {
                    contains = false;
                    break;
                } else {
                    wordNumberCopy.put(substring, number - 1);
                }
            }
            if (contains) {
                result.add(i);
            }
        }
        return result;
    }

    private Map<String, Integer> initWordMap(String[] words) {
        Map<String, Integer> result = new HashMap<>();
        for (String word : words) {
            result.put(word, result.getOrDefault(word, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        SubStringWithConcatenationOfAllWords_30 subStr = new SubStringWithConcatenationOfAllWords_30();
        System.out.println(subStr.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(subStr.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
        System.out.println(subStr.findSubstring("wordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestwordwordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
    }
}

