package com.xiaotian.demo.algorithm.leetcode;

public class A_0 {

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    public static void main(String[] args) {
        A_0 a_0 = new A_0();
        System.out.println(a_0.isPowerOfThree(0));
        System.out.println(a_0.isPowerOfThree(1));
        System.out.println(a_0.isPowerOfThree(2));
        System.out.println(a_0.isPowerOfThree(3));
        System.out.println(a_0.isPowerOfThree(4));
        System.out.println(a_0.isPowerOfThree(9));
        System.out.println(1 % 3);
    }
}
