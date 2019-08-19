package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class A_97_Interleaving_StringTest {

    private A_97_Interleaving_String a_97_interleaving_string;

    @Before
    public void init() {
        a_97_interleaving_string = new A_97_Interleaving_String();
    }

    @Test
    public void isInterleave() {

        assertTrue(a_97_interleaving_string.isInterleave("", "", ""));
        assertTrue(a_97_interleaving_string.isInterleave(null, "", ""));
        assertTrue(a_97_interleaving_string.isInterleave("", null, ""));
        assertTrue(a_97_interleaving_string.isInterleave("", "", null));
        assertTrue(a_97_interleaving_string.isInterleave(null, null, ""));
        assertTrue(a_97_interleaving_string.isInterleave("", null, null));
        assertTrue(a_97_interleaving_string.isInterleave(null, null, null));

        assertTrue(a_97_interleaving_string.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        assertFalse(a_97_interleaving_string.isInterleave("aabcc", "dbbca", "aadbbbaccc"));

    }
}