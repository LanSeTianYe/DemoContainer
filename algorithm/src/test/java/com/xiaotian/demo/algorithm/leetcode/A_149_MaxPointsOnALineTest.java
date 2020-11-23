package com.xiaotian.demo.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class A_149_MaxPointsOnALineTest {

    @Test
    public void maxPoints() {
        int count;
        A_149_MaxPointsOnALine aLine = new A_149_MaxPointsOnALine();

        count = aLine.maxPoints(null);
        Assert.assertEquals(0, count);

        count = aLine.maxPoints(new int[][]{});
        Assert.assertEquals(0, count);

        count = aLine.maxPoints(new int[][]{{0, 0}});
        Assert.assertEquals(1, count);

        count = aLine.maxPoints(new int[][]{{0, 0}, {0, 0}});
        Assert.assertEquals(2, count);

        count = aLine.maxPoints(new int[][]{{0, 0}, {1, 65536}, {65536, 0}});
        Assert.assertEquals(2, count);


        count = aLine.maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}});
        Assert.assertEquals(3, count);


        count = aLine.maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}});
        Assert.assertEquals(4, count);

        count = aLine.maxPoints(new int[][]{{1, 1}, {1, 1}, {0, 0}, {3, 4}, {4, 5}, {5, 6}, {7, 8}, {8, 9}});
        Assert.assertEquals(5, count);

    }
}
