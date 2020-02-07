package com.xiaotian.demo.algorithm.leetcode;

public class A_9_PalindromeNumber {

    public boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }

        int palindromeNumber = 0;
        int originalNumber = x;
        int remainder = 0;
        do {
            remainder = originalNumber % 10;
            palindromeNumber = palindromeNumber * 10 + remainder;
            originalNumber = originalNumber / 10;
        } while (originalNumber > 0);

        return x == palindromeNumber;
    }


}
