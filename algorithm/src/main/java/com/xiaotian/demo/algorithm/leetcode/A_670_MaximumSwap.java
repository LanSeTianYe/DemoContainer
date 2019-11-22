package com.xiaotian.demo.algorithm.leetcode;

public class A_670_MaximumSwap {

    public int maximumSwap(int num) {
        if (num <= 11) {
            return num;
        }
        char[] numCharArr = String.valueOf(num).toCharArray();
        int canSwapIndex = getCanSwapIndex(numCharArr);
        if (-1 == canSwapIndex) {
            return num;
        }
        int lastMaxCharIndexFromCanSwapIndex = getLastMaxCharIndexFromCanSwapIndex(canSwapIndex, numCharArr);
        int lessThanMaxCharIndex = getFirstLessThanMaxCharIndex(numCharArr, numCharArr[lastMaxCharIndexFromCanSwapIndex]);
        swap(numCharArr, lessThanMaxCharIndex, lastMaxCharIndexFromCanSwapIndex);
        return Integer.valueOf(String.valueOf(numCharArr));
    }

    private int getCanSwapIndex(char[] numCharArr) {
        for (int i = 0; i < numCharArr.length - 1; i++) {
            if (numCharArr[i] < numCharArr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    private int getLastMaxCharIndexFromCanSwapIndex(int canSwapIndex, char[] numCharArr) {
        char max = numCharArr[canSwapIndex];
        int index = canSwapIndex;
        for (int i = canSwapIndex; i < numCharArr.length; i++) {
            if (max <= numCharArr[i]) {
                max = numCharArr[i];
                index = i;
            }
        }
        return index;
    }

    private int getFirstLessThanMaxCharIndex(char[] numCharArr, char maxChar) {
        for (int i = 0; i < numCharArr.length - 1; i++) {
            if (numCharArr[i] < maxChar) {
                return i;
            }
        }
        // not exec
        return -1;
    }

    private void swap(char[] numCharArr, int indexA, int indexB) {
        char temp = numCharArr[indexA];
        numCharArr[indexA] = numCharArr[indexB];
        numCharArr[indexB] = temp;
    }
}
