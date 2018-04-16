package com.sun.xiaotian.demo.test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 *  结论:每次创建对象耗时基本相同，所以创建5次比创建一次耗时5倍
 *  创建一个日期对象大概需要耗费3纳秒
 CreateObjectEveryTimes: 720593
 UseOldObjectEveryTimes: 365
 times: 1 createObjectEveryTimes/useOldObjectEveryTimes: 1974.227397260274
 createOneObjectCostTime: 365
 CreateObjectEveryTimes: 5105
 UseOldObjectEveryTimes: 1094
 times: 10 createObjectEveryTimes/useOldObjectEveryTimes: 4.66636197440585
 createOneObjectCostTime: 109
 CreateObjectEveryTimes: 1635557
 UseOldObjectEveryTimes: 13858
 times: 100 createObjectEveryTimes/useOldObjectEveryTimes: 118.02258623177947
 createOneObjectCostTime: 138
 CreateObjectEveryTimes: 228650
 UseOldObjectEveryTimes: 52513
 times: 1000 createObjectEveryTimes/useOldObjectEveryTimes: 4.354159922304953
 createOneObjectCostTime: 52
 CreateObjectEveryTimes: 1433164
 UseOldObjectEveryTimes: 338416
 times: 10000 createObjectEveryTimes/useOldObjectEveryTimes: 4.234917970781523
 createOneObjectCostTime: 33
 CreateObjectEveryTimes: 12320467
 UseOldObjectEveryTimes: 2512595
 times: 100000 createObjectEveryTimes/useOldObjectEveryTimes: 4.903483052382099
 createOneObjectCostTime: 25
 CreateObjectEveryTimes: 19551562
 UseOldObjectEveryTimes: 4462500
 times: 1000000 createObjectEveryTimes/useOldObjectEveryTimes: 4.3813024089635855
 createOneObjectCostTime: 4
 CreateObjectEveryTimes: 195262537
 UseOldObjectEveryTimes: 39395226
 times: 10000000 createObjectEveryTimes/useOldObjectEveryTimes: 4.956502521396882
 createOneObjectCostTime: 3
 CreateObjectEveryTimes: 1941729314
 UseOldObjectEveryTimes: 362286644
 times: 100000000 createObjectEveryTimes/useOldObjectEveryTimes: 5.359649178786729
 createOneObjectCostTime: 3
 */
public class TestNewDate {

    private static int times = 1;

    public static void main(String[] args) {
        while (times < 100000000) {
            long createObjectEveryTimes = testCreateObjectEveryTimes();
            long useOldObjectEveryTimes = testUseOldObjectEveryTimes();

            System.out.println("CreateObjectEveryTimes: " + createObjectEveryTimes);
            System.out.println("UseOldObjectEveryTimes: " + useOldObjectEveryTimes);

            System.out.println(String.format("times: %s createObjectEveryTimes/useOldObjectEveryTimes: %s", times, (createObjectEveryTimes * 1.0 / useOldObjectEveryTimes)));
            System.out.println(String.format("createOneObjectCostTime: %s", useOldObjectEveryTimes / times));

            times = times * 10;
        }
    }

    private static long testCreateObjectEveryTimes() {
        long startTime = System.nanoTime();
        Date date;
        for (int i = 0; i < times; i++) {
           date = new Date();
           date = new Date();
           date = new Date();
           date = new Date();
           date = new Date();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long testUseOldObjectEveryTimes() {
        long startTime = System.nanoTime();
        Date date = new Date();
        for (int i = 0; i < times; i++) {
            date = new Date();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
