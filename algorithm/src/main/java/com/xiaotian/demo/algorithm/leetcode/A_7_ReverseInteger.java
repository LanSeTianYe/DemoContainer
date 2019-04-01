package com.xiaotian.demo.algorithm.leetcode;

/**
 * https://leetcode.com/problems/reverse-integer/
 * 反转整数：需要注意整数范围
 */
public class A_7_ReverseInteger {

    public int reverse(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return (int) result == result ? (int) result : 0;
    }

    public static void main(String[] args) {
        A_7_ReverseInteger reverseInteger_7 = new A_7_ReverseInteger();
        System.out.println(reverseInteger_7.reverse(1));
        System.out.println(reverseInteger_7.reverse(1));
        System.out.println(reverseInteger_7.reverse(Integer.MAX_VALUE));
        System.out.println(reverseInteger_7.reverse(2147483647));
        System.out.println(reverseInteger_7.reverse(1534236469));
        System.out.println(reverseInteger_7.reverse(1534236412));
        System.out.println(reverseInteger_7.reverse(Integer.MIN_VALUE));
        System.out.println(reverseInteger_7.reverse(-123));
        System.out.println(-11 / 10);
        System.out.println(-1112312 / 100);
    }
}
