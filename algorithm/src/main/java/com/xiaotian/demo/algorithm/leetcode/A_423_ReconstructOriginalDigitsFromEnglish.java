package com.xiaotian.demo.algorithm.leetcode;

public class A_423_ReconstructOriginalDigitsFromEnglish {

    /**
     * 单词字母的唯一性，前面数字包含后面字母不包含的字母
     **/
    private String[] NUMBERS_STR_LIST = new String[]{"eight", "two", "three", "six", "zero", "seven", "four", "one", "five", "nine"};
    /**
     * 数字单词对应的数字
     **/
    private int[] NUMBERS_LIST = new int[]{8, 2, 3, 6, 0, 7, 4, 1, 5, 9};

    public String originalDigits(String s) {
        StringBuilder result = new StringBuilder();
        int[] chars = new int[26];
        //统计字符出现次数
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            chars[index] += 1;
        }

        int[] numberTimes = new int[10];
        //统计每个数字出现的次数
        int minNumber = 0;
        for (int i = 0; i < NUMBERS_STR_LIST.length; i++) {
            minNumber = getMinNumber(chars, NUMBERS_STR_LIST[i]);
            reduceNumber(chars, NUMBERS_STR_LIST[i], minNumber);
            numberTimes[NUMBERS_LIST[i]] = minNumber;
        }
        //构造字符串
        for (int i = 0; i < numberTimes.length; i++) {
            for (int j = 0; j < numberTimes[i]; j++) {
                result.append(i);
            }
        }
        return result.toString();
    }

    private int getMinNumber(int[] chars, String numberStr) {
        int result = Integer.MAX_VALUE;
        for (char c : numberStr.toCharArray()) {
            int index = c - 'a';
            result = Math.min(chars[index], result);
        }
        return result;
    }

    private void reduceNumber(int[] chars, String numberStr, int number) {
        for (char c : numberStr.toCharArray()) {
            int index = c - 'a';
            chars[index] -= number;
        }
    }
}
