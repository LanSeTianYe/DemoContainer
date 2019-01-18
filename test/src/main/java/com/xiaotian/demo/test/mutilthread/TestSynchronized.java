package com.xiaotian.demo.test.mutilthread;


import java.util.concurrent.TimeUnit;

public class TestSynchronized {

    public static void main(String[] args) {
        InnerSynchronized innerSynchronized = new InnerSynchronized();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                innerSynchronized.sayHello();
            }).start();
        }
    }

    public static class InnerSynchronized {
        public synchronized void sayHello() {
            System.out.println("Hello, Name");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
