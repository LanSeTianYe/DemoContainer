package com.sun.xiaotian.demo.hystrix.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PercentUtilTest {

    PercentUtil percentUtil = PercentUtil.ofSuccessAndFaildTimes(99, 1);

    @Test
    public void next() {
        int times = 100000;
        int failTimes = 0;
        for (int i = 0; i < times; i++) {
            if (!percentUtil.next()) {
                failTimes++;
            }
        }
        System.out.println(failTimes);
    }
}