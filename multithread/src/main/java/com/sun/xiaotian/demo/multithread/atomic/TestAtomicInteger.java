package com.sun.xiaotian.demo.multithread.atomic;


import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class TestAtomicInteger {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestInteger> ageUpdater = AtomicIntegerFieldUpdater.newUpdater(TestInteger.class, "age");
        TestInteger testInteger = new TestInteger();
        ageUpdater.addAndGet(testInteger, 100);
        System.out.println(ageUpdater.get(testInteger));

        AtomicReference<TestInteger> reference = new AtomicReference<TestInteger>();
        TestInteger newTest = new TestInteger();
        reference.compareAndSet(testInteger, newTest);

        AtomicIntegerArray integerArray = new AtomicIntegerArray(10);
        integerArray.addAndGet(1, 10);

    }

    private static class TestInteger {
        volatile int age = 100;
    }
}
