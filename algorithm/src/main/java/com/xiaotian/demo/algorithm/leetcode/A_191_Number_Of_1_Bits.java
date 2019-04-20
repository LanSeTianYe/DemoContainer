package com.xiaotian.demo.algorithm.leetcode;

/**
 * <a href = "https://leetcode.com/problems/number-of-1-bits/">Number of 1 Bits</a>
 */
public class A_191_Number_Of_1_Bits {

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        A_191_Number_Of_1_Bits numberOf1Bits = new A_191_Number_Of_1_Bits();
        System.out.println(numberOf1Bits.hammingWeight(-3));
        System.out.println(numberOf1Bits.hammingWeight(1));
        System.out.println(numberOf1Bits.hammingWeight(2));
        System.out.println(numberOf1Bits.hammingWeight(3));
        System.out.println(numberOf1Bits.hammingWeight(4));
        System.out.println(numberOf1Bits.hammingWeight(5));
        System.out.println(numberOf1Bits.hammingWeight(6));
    }
}
