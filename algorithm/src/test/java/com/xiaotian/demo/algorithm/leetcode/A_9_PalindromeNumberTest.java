package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class A_9_PalindromeNumberTest {

    @Test
    public void isPalindrome() {
        A_9_PalindromeNumber palindromeNumber = new A_9_PalindromeNumber();
        assertFalse(palindromeNumber.isPalindrome(-1));
        assertFalse(palindromeNumber.isPalindrome(-12));
        assertFalse(palindromeNumber.isPalindrome(12));
        assertFalse(palindromeNumber.isPalindrome(123));


        assertTrue(palindromeNumber.isPalindrome(0));
        assertTrue(palindromeNumber.isPalindrome(121));
        assertTrue(palindromeNumber.isPalindrome(1221));
        assertTrue(palindromeNumber.isPalindrome(123321));
    }
}