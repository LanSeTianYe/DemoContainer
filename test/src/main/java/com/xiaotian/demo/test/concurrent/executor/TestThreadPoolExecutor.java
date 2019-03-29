package com.xiaotian.demo.test.concurrent.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolExecutor {

    public static void main(String[] args) throws InterruptedException {
        int taskNumber = 6;
        CountDownLatch countDownLatch = new CountDownLatch(taskNumber);
        int coreSize = 2;
        int queueSize = 2;

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                coreSize,
                coreSize,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(queueSize));

        //当队列满了之后，在当前线程执行任务， 现在6个任务线程池中每次执行两个，当前线程每次执行一个
        /** 执行结果
         start: 1553853242
         main : 4
         pool-1-thread-2 : 1
         pool-1-thread-1 : 0
         main : 5
         pool-1-thread-1 : 3
         pool-1-thread-2 : 2
         end  : 1553853248
         true
         */
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println("start: " + System.currentTimeMillis() / 1000);
        for (int i = 0; i < taskNumber; i++) {
            threadPoolExecutor.submit(new SleepTask(countDownLatch, i));
        }
        countDownLatch.await();
        System.out.println("end  : " + System.currentTimeMillis() / 1000);

        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminating()) {
            TimeUnit.MILLISECONDS.sleep(100);
        }

        System.out.println(threadPoolExecutor.isTerminating());
    }

}

class SleepTask implements Runnable {

    private final CountDownLatch countDownLatch;
    private final int num;
    private boolean interrupted = false;

    SleepTask(CountDownLatch countDownLatch, int num) {
        this.countDownLatch = countDownLatch;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " : " + num);
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            interrupted = true;
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
