package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class A_763_PartitionLabelsTest {

    private A_763_PartitionLabels a_763_partitionLabels;

    @Before
    public void setUp() throws Exception {
        this.a_763_partitionLabels = new A_763_PartitionLabels();
    }

    @Test
    public void partitionLabels() {
        Integer[] result = a_763_partitionLabels.partitionLabels(null).toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{}, result);

        result = a_763_partitionLabels.partitionLabels("").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{}, result);

        result = a_763_partitionLabels.partitionLabels("abc").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{1, 1, 1}, result);

        result = a_763_partitionLabels.partitionLabels("abac").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{3, 1}, result);

        result = a_763_partitionLabels.partitionLabels("ababcbacadefegdehijhklij").toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{9, 7, 8}, result);
    }
}