package com.xiaotian.demo.test.concurrent.c_future;


import com.xiaotian.demo.test.util.TimeStatisticsUtil;
import java.util.ArrayList;
import java.util.List;


public class TestParallelStream {

    public static void main(String[] args) {

        TimeStatisticsUtil.startTask("parallelStream");
        List<Pig> pigList = new ArrayList<>();
        int count = 10;
        for (int i = 0; i < count; i++) {
            pigList.add(new Pig("pig_" + i));
        }

        pigList.parallelStream().forEach(Pig::sleep);
        TimeStatisticsUtil.endTask();

        TimeStatisticsUtil.startTask("stream");
        pigList.forEach(Pig::sleep);
        TimeStatisticsUtil.endTask();

        TimeStatisticsUtil.showResult();
    }
}
