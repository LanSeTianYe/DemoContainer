package com.xiaotian.demo.test.thread.pool;


import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

    private static final ThreadPoolExecutor THREAD_POOL = ThreadPoolFactory.getCpu("TestThreadPool");

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            THREAD_POOL.submit(new Task());
        }

    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
