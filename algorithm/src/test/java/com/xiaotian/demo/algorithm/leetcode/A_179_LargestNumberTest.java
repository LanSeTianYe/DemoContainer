package com.xiaotian.demo.algorithm.leetcode;

import com.xiaotian.demo.algorithm.util.ArrayUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class A_179_LargestNumberTest {

    private static A_179_LargestNumber largestNumber;

    @BeforeClass
    public static void init() {
        largestNumber = new A_179_LargestNumber();

    }

    @Test
    public void largestNumber() {
        assertEquals("", largestNumber.largestNumber(new int[]{}));
        assertEquals("0", largestNumber.largestNumber(new int[]{0, 0, 0}));
        assertEquals("3", largestNumber.largestNumber(new int[]{3}));
        assertEquals("321", largestNumber.largestNumber(new int[]{1, 2, 3}));
        assertEquals("535353533", largestNumber.largestNumber(new int[]{53, 535353, 3}));
        assertEquals("12121", largestNumber.largestNumber(new int[]{12, 121}));
    }

    @Test
    public void largestNumber_notUseStream() {
        assertEquals("", largestNumber.largestNumber_notUseStream(new int[]{}));
        assertEquals("0", largestNumber.largestNumber_notUseStream(new int[]{0, 0, 0}));
        assertEquals("3", largestNumber.largestNumber_notUseStream(new int[]{3}));
        assertEquals("321", largestNumber.largestNumber_notUseStream(new int[]{1, 2, 3}));
        assertEquals("535353533", largestNumber.largestNumber_notUseStream(new int[]{53, 535353, 3}));
        assertEquals("12121", largestNumber.largestNumber_notUseStream(new int[]{12, 121}));
    }

    @Test
    public void timeTest() {
        int arrCount = 2500;
        int[][] arrMatrix = new int[arrCount][];
        for (int i = 0; i < arrCount; i++) {
            arrMatrix[i] = ArrayUtil.generateByLength(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < arrCount; i++) {
            largestNumber.largestNumber(arrMatrix[i]);
        }
        long end = System.nanoTime();

        System.out.println(end - start);

        start = System.nanoTime();
        for (int i = 0; i < arrCount; i++) {
            largestNumber.largestNumber_notUseStream(arrMatrix[i]);
        }
        end = System.nanoTime();
        System.out.println(end - start);
    }
}