package com.sun.xiaotian.demo.multithread;

import java.util.concurrent.atomic.AtomicInteger;

public class PerformanceTest {

    private static volatile int volatileNumber = 0;

    public static void main(String[] args) {
        int times = 1000000000;
        long t1 = System.currentTimeMillis();
        addInt(times);
        long t2 = System.currentTimeMillis();
        //2
        System.out.println(String.format("addInt: %s", t2 - t1));

        addAtomicInteger(times);
        long t3 = System.currentTimeMillis();
        //5732
        System.out.println(String.format("addAtomicInteger: %s", t3 - t2));

        addWithSynchronized(times);
        long t4 = System.currentTimeMillis();
        //20583
        System.out.println(String.format("addWithSynchronized: %s", t4 - t3));

        addVolatile(times);
        long t5 = System.currentTimeMillis();
        //6113
        System.out.println(String.format("addVolatile: %s", t5 - t4));
    }


    private static void addInt(int times) {
        int number = 0;
        for (int i = 0; i < times; i++) {
            number = number + 1;
        }
    }

    private static void addAtomicInteger(int times) {
        AtomicInteger number = new AtomicInteger(0);
        for (int i = 0; i < times; i++) {
            number.getAndIncrement();
        }
    }

    private static void addWithSynchronized(int times) {
        for (int i = 0; i < times; i++) {
            addSynchronized(i);
        }
    }

    private static void addVolatile(int times) {
        for (int i = 0; i < times; i++) {
             volatileNumber = volatileNumber+ 1;
        }
    }

    private static synchronized void addSynchronized(int number) {
        number = number + 1;
    }
}
