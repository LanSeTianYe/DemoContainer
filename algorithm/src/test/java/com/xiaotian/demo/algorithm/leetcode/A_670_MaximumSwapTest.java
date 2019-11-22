package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class A_670_MaximumSwapTest {

    private final A_670_MaximumSwap maximumSwap = new A_670_MaximumSwap();

    @Test
    public void maximumSwap() {

        Assert.assertEquals(0, maximumSwap.maximumSwap(0));
        Assert.assertEquals(1, maximumSwap.maximumSwap(1));
        Assert.assertEquals(511, maximumSwap.maximumSwap(115));
        Assert.assertEquals(511, maximumSwap.maximumSwap(511));
        Assert.assertEquals(988321, maximumSwap.maximumSwap(988321));
        Assert.assertEquals(998323181, maximumSwap.maximumSwap(988323191));

    }
}