package com.xiaotian.demo.algorithm.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 基数排序
 */
public class RadixSort {

    public static void radixSortString(String[] words, int length) {
        if (null == words) {
            return;
        }
        if (!checkLength(words, length)) {
            throw new IllegalArgumentException("some word's length is not equal length");
        }

        List<List<String>> buckets = new ArrayList<>(256);

        for (int i = 0; i < 256; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int i = 0; i < length; i++) {
            // in buckets
            for (String word : words) {
                buckets.get(word.charAt(length - i - 1)).add(word);
            }
            // out buckets
            int index = 0;
            for (int j = 0; j < buckets.size(); j++) {
                for (String s : buckets.get(j)) {
                    words[index++] = s;
                }
                buckets.get(j).clear();
            }
        }
    }

    private static boolean checkLength(String[] words, int length) {
        for (String word : words) {
            if (word.length() != length) {
                return false;
            }
        }
        return true;
    }
}
