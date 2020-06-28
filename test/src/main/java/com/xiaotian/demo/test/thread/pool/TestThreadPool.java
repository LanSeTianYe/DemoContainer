package com.xiaotian.demo.test.thread.pool;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadPool {

    public static void main(String[] args) {

        int coreSize = Runtime.getRuntime().availableProcessors();
        int keepAliaveTime = 5;

        PriorityBlockingQueue<Runnable> priorityBlockingQueue = new PriorityBlockingQueue<>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            coreSize,
            coreSize,
            keepAliaveTime,
            TimeUnit.MINUTES,
            priorityBlockingQueue,
            (r) -> {
                Thread thread = new Thread(r);
                thread.setName("Pool-Thread");
                return thread;
            }
        );

        while (true) {
            threadPoolExecutor.execute(new MyTask());
        }


    }


    static class MyTask implements Runnable, Comparable<MyTask> {

        private static final AtomicInteger idGenerate = new AtomicInteger(0);

        private int id;

        public MyTask() {
            this.id = idGenerate.addAndGet(1);
        }

        @Override
        public int compareTo(@NotNull MyTask o) {
            return o.id - this.id;
        }

        @Override
        public void run() {
            System.out.println("id: " + id + "\tthread: " + Thread.currentThread().getId());
        }
    }


}
