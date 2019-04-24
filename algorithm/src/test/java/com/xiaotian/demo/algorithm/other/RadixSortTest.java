package com.xiaotian.demo.algorithm.other;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RadixSortTest {


    @Test
    public void radixSortString() {
        String[] words = new String[]{"abc", "cba", "ccc", "cab", "acb"};
        RadixSort.radixSortString(words, 3);

        assertEquals("abc", words[0]);
        assertEquals("acb", words[1]);
    }
}