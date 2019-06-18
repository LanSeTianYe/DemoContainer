package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_395_LongestSubstringWithAtLeastKRepeatingCharactersTest {

    private A_395_LongestSubstringWithAtLeastKRepeatingCharacters repeatingCharacters;

    @Before
    public void setUp() {
        repeatingCharacters = new A_395_LongestSubstringWithAtLeastKRepeatingCharacters();
    }

    @Test
    public void longestSubstring() {
        //null
        assertEquals(0, repeatingCharacters.longestSubstring(null, 1));
        //""
        assertEquals(0, repeatingCharacters.longestSubstring("", 3));

        assertEquals(0, repeatingCharacters.longestSubstring("", 0));
        assertEquals(0, repeatingCharacters.longestSubstring("", -1));
        assertEquals(3, repeatingCharacters.longestSubstring("abc", 0));
        assertEquals(3, repeatingCharacters.longestSubstring("abc", -1));


        assertEquals(3, repeatingCharacters.longestSubstring("aaabb", 3));
        assertEquals(3, repeatingCharacters.longestSubstring("aaabb", 3));
        assertEquals(5, repeatingCharacters.longestSubstring("ababbc", 2));
        assertEquals(0, repeatingCharacters.longestSubstring("ababbc", 3));
        assertEquals(0, repeatingCharacters.longestSubstring("ababbc", 3));

        assertEquals(6, repeatingCharacters.longestSubstring("ababbac", 3));
    }
}