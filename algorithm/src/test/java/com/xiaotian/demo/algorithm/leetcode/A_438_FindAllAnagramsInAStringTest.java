package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class A_438_FindAllAnagramsInAStringTest {

    private A_438_FindAllAnagramsInAString a_438_findAllAnagramsInAString;

    @Before
    public void init() {
        this.a_438_findAllAnagramsInAString = new A_438_FindAllAnagramsInAString();
    }

    @Test
    public void findAnagrams() {
        Integer[] result;

        result = a_438_findAllAnagramsInAString.findAnagrams("", "").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{}, result);

        result = a_438_findAllAnagramsInAString.findAnagrams("aa", "").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{}, result);

        result = a_438_findAllAnagramsInAString.findAnagrams("", "a").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{}, result);

        result = a_438_findAllAnagramsInAString.findAnagrams("aaaaa", "aaaaaaaaa").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{}, result);

        result = a_438_findAllAnagramsInAString.findAnagrams("aaaaa", "aaaaa").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{0}, result);

        result = a_438_findAllAnagramsInAString.findAnagrams("cbaebabacd", "abc").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{0, 6}, result);

        result = a_438_findAllAnagramsInAString.findAnagrams("abab", "ab").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{0, 1, 2}, result);
    }
}