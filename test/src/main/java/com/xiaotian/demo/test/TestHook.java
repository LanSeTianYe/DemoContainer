package com.xiaotian.demo.test;


import java.util.concurrent.TimeUnit;

public class TestHook {

    public static void main(String[] args) throws InterruptedException {
        TestHook testHook = new TestHook();
        testHook.addHook();
        testHook.addHook();
        testHook.addHook();
        testHook.run();
    }

    public void run() throws InterruptedException {
        System.out.println("run ...");
        System.exit(1);
        TimeUnit.SECONDS.sleep(10);
    }

    public void addHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("shutdown hook run ...");
            }
        });
    }
}
