package com.sun.xiaotian.demo.test;


import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.clock.CpuClock;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.HtmlReportModule;
import org.databene.contiperf.timer.RandomTimer;
import org.junit.Rule;
import org.junit.Test;

public class TestContiPerf {

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule(new HtmlReportModule());


    @Test
    @PerfTest(invocations = 10000, threads = 20)
    @Required(max = 100, average = 1)
    public void test_调用指定次数() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    @PerfTest(duration = 1000, threads = 20)
    @Required(max = 100, average = 1)
    public void test_执行指定时间() {

    }

    @Test
    @PerfTest(invocations = 20, threads = 10, timer = RandomTimer.class, timerParams = {1000,2000})
    @Required(max = 2, average = 1)
    public void test_调用指定次数_线程执行完成之后等待指定时间在执行() {
        System.out.println(System.currentTimeMillis());
    }


    @Test
    @PerfTest(duration = 3000, threads = 10, rampUp = 100, warmUp = 1000)
    @Required(max = 100, average = 1)
    public void test_调用指定次数_每隔100毫秒启动一个线程_运行1000毫秒之后开始计数() {
        System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis());
    }

    @Test
    @PerfTest(invocations = 10000, threads = 20, clocks = CpuClock.class)
    @Required(max = 100, average = 1)
    public void test_CpuClock() {
        System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis());
    }
}
