package com.xiaotian.demo.test.thread;

import java.util.concurrent.TimeUnit;

public class TestAwaitSignal {

    private final static Object lockA = new Object();
    private final static Object lockB = new Object();
    private final static Object lockC = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (lockA) {
                    try {
                        lockA.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A");
                synchronized (lockB) {
                    lockB.notifyAll();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (lockB) {
                    try {
                        lockB.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B");
                synchronized (lockC) {
                    lockC.notifyAll();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                synchronized (lockC) {
                    try {
                        lockC.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("C");
                synchronized (lockA) {
                    lockA.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        TimeUnit.SECONDS.sleep(1);
        synchronized (lockA) {
            lockA.notify();
        }
    }
}
