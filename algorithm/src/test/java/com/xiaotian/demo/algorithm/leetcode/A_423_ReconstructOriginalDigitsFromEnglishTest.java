package com.xiaotian.demo.algorithm.leetcode;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_423_ReconstructOriginalDigitsFromEnglishTest {

    private static A_423_ReconstructOriginalDigitsFromEnglish digitNumber;

    @BeforeClass
    public static void init() {
        digitNumber = new A_423_ReconstructOriginalDigitsFromEnglish();
    }

    @Test
    public void originalDigits() {
        assertEquals("012", digitNumber.originalDigits("owoztneoer"));
        assertEquals("45", digitNumber.originalDigits("fviefuro"));
        assertEquals("0123456789", digitNumber.originalDigits("zeroonetwothreefourfivesixseveneightnine"));
    }
}