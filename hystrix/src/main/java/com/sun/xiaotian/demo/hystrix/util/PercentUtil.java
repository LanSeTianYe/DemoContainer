package com.sun.xiaotian.demo.hystrix.util;

import java.util.Random;

public class PercentUtil {

    private final static Random random = new Random();

    private final int successTimes;
    private final int failTimes;

    private PercentUtil(int successTimes, int failTimes) {
        this.successTimes = successTimes;
        this.failTimes = failTimes;
    }


    public static PercentUtil ofSuccessAndFaildTimes(int successTimes, int failTimes) {
        return new PercentUtil(successTimes, failTimes);
    }

    public boolean next() {
        return random.nextInt(successTimes + failTimes) < successTimes;
    }
}
