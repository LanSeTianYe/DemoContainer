package com.sun.xiaotian.demo.test;


import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.clock.CpuClock;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.CSVLatencyReportModule;
import org.databene.contiperf.report.HtmlReportModule;
import org.databene.contiperf.timer.RandomTimer;
import org.junit.Rule;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestContiPerf {

    private final Random random = new Random();

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule(new HtmlReportModule(), new CSVLatencyReportModule());

    @Test
    @PerfTest(invocations = 100, threads = 20)
    @Required(max = 105, average = 60, throughput = 20 * (1000/60), totalTime = 10000)
    public void test_调用指定次数() {
        sleep();
    }

    @Test
    @PerfTest(duration = 1000, threads = 20)
    @Required(max = 105, average = 60)
    public void test_执行指定时间() {
        sleep();
    }

    @Test
    @PerfTest(invocations = 10, threads = 1, timer = RandomTimer.class, timerParams = {1000,2000})
    @Required(max = 105, average = 60)
    public void test_调用指定次数_线程执行完成之后等待指定时间在执行() {
        sleep();
        System.out.println(System.currentTimeMillis());
    }


    @Test
    @PerfTest(duration = 5000, threads = 10, rampUp = 100, warmUp = 1000)
    @Required(max = 105, average = 60)
    public void test_调用指定次数_每隔100毫秒启动一个线程_运行1000毫秒之后开始计数() {
        sleep();
    }

    @Test
    @PerfTest(invocations = 100, threads = 20, clocks = CpuClock.class)
    @Required(max = 105, average = 60)
    public void test_CpuClock() {
        sleep();
    }

    private void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
