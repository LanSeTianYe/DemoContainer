package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/count-numbers-with-unique-digits/">Count Numbers with Unique Digits</a>
 */
public class A_357_CountNumbersWithUniqueDigits {

    public int countNumbersWithUniqueDigits(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = result + getCount(i);
        }
        return result + 1;
    }

    private int getCount(int length) {
        int count = 9;
        for (int i = 1; (i < length && ((10 - i) > 1)); i++) {
            count = count * (10 - i);
        }
        return count;
    }

    public static void main(String[] args) {
        A_357_CountNumbersWithUniqueDigits count = new A_357_CountNumbersWithUniqueDigits();
        System.out.println(count.countNumbersWithUniqueDigits(1));
        System.out.println(count.countNumbersWithUniqueDigits(2));
        System.out.println(count.countNumbersWithUniqueDigits(3));
        System.out.println(count.countNumbersWithUniqueDigits(4));
        System.out.println(count.countNumbersWithUniqueDigits(5));
    }
}
