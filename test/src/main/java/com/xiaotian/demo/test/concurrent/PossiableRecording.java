package com.xiaotian.demo.test.concurrent;

public class PossiableRecording {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread_One = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                x = b;
            }
        });

        Thread thread_Two = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 1;
                y = a;
            }
        });

        thread_One.setName("Thread_One");
        thread_Two.setName("Thread_Two");

        thread_One.start();
        thread_Two.start();

        thread_One.join();
        thread_Two.join();
        System.out.println(String.format("x: %s, y: %s", x, y));
    }
}
