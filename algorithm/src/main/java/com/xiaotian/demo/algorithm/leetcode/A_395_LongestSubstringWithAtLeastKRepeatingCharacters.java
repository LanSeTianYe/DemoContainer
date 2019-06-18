package com.xiaotian.demo.algorithm.leetcode;

public class A_395_LongestSubstringWithAtLeastKRepeatingCharacters {

    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (k <= 1) {
            return s.length();
        }

        boolean[] splitFLag = new boolean[s.length() + 1];
        splitFLag[s.length()] = true;
        markSplitIndex(splitFLag, s.toCharArray(), 0, s.length() - 1, k);
        return getMaxSubStringLength(splitFLag);
    }

    private int getMaxSubStringLength(boolean[] splitFlag) {
        int result = 0;
        int sum = 0;
        for (int i = 0; i < splitFlag.length; i++) {
            if (!splitFlag[i]) {
                sum++;
            } else {
                result = Math.max(result, sum);
                sum = 0;
            }
        }
        result = Math.max(result, sum);
        return result;
    }

    private void markSplitIndex(boolean[] splitFlag, char[] sArr, int start, int end, int k) {
        int subStrLength = end - start + 1;
        if (subStrLength < k) {
            for (int i = start; i <= end; i++) {
                splitFlag[i] = true;
            }
            return;
        }

        int[] charCount = new int[26];
        for (int i = start; i <= end; i++) {
            charCount[sArr[i] - 'a'] = 0;
        }
        for (int i = start; i <= end; i++) {
            charCount[sArr[i] - 'a']++;
        }

        boolean hasFlag = false;

        for (int i = start; i <= end; i++) {
            if (charCount[sArr[i] - 'a'] < k) {
                hasFlag = true;
                splitFlag[i] = true;
            }
        }
        if (hasFlag) {
            for (int i = start; i <= end; ) {
                int left = getNextNoSplitFlag(splitFlag, i, end);
                if (left < 0) {
                    return;
                }
                int right = getLastNoSplitFlag(splitFlag, left, end);
                markSplitIndex(splitFlag, sArr, left, right, k);
                i = right + 1;
            }
        }
    }

    private int getNextNoSplitFlag(boolean[] splitFlag, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (!splitFlag[i]) {
                return i;
            }
        }
        return -1;
    }

    private int getLastNoSplitFlag(boolean[] splitFlag, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (splitFlag[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}
