package com.xiaotian.demo.algorithm.other;

import com.xiaotian.demo.algorithm.util.ArrayUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {

    private static QuickSort quickSort;
    private static int length;

    @BeforeClass
    public static void init() {
        quickSort = new QuickSort();
        length = 1000000;
    }

    @Test
    public void quickSort() {
        for (int i = 0; i < length; i++) {
            int[] arr = ArrayUtil.generateByLength(i);
            long start = System.nanoTime();
            quickSort.quickSort(arr);
            long end = System.nanoTime();
            System.out.println(String.format("length: %s, costTime:%s(ns)", i, end - start));
            assertTrue(ArrayUtil.isAsc(arr));
        }
    }
}