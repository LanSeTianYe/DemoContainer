package com.xiaotian.demo.algorithm.other;

import java.util.ArrayList;

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

        ArrayList<String>[] buckets = new ArrayList[256];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i = 0; i < length; i++) {
            //in buckets
            for (String word : words) {
                buckets[word.charAt(length - i - 1)].add(word);
            }
            //out buckets
            int index = 0;
            for (int j = 0; j < buckets.length; j++) {
                for (String s : buckets[j]) {
                    words[index++] = s;
                }
                buckets[j].clear();
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

