package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class A_720_LongestWordInDictionaryTest {

    private A_720_LongestWordInDictionary longestWordInDictionary;

    @Before
    public void init() {
        longestWordInDictionary = new A_720_LongestWordInDictionary();
    }

    @Test
    public void test() {
        String longestWord;

        longestWord = longestWordInDictionary.longestWord(new String[]{});
        Assert.assertEquals("", longestWord);

        longestWord = longestWordInDictionary.longestWord(new String[]{"w", "wo", "wor", "worl", "world"});
        Assert.assertEquals("world", longestWord);

        longestWord = longestWordInDictionary.longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"});
        Assert.assertEquals("apple", longestWord);

        longestWord = longestWordInDictionary.longestWord(new String[]{"a", "banana", "app", "ap", "apply", "apple"});
        Assert.assertEquals("app", longestWord);
    }
}
