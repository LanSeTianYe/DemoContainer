package com.xiaotian.demo.test.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestExecutors {

    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        singleThreadExecutor.submit(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("run-1: " + i);
            }
        });

        singleThreadExecutor.shutdown();
        try {
            singleThreadExecutor.submit(() -> {
                for (int i = 0; i < 10; i++) {
                    System.out.println("run-2: " + i);
                }
            });
        } catch (Exception e) {
            System.out.println("ExecutorService has shutdown, reject accept new task!");
            System.out.println("Exception message: " + e.getMessage());
        }

        Runtime.getRuntime().addShutdownHook(new ShutdownThread(singleThreadExecutor));
    }
}

class ShutdownThread extends Thread {

    private final ExecutorService executorService;

    public ShutdownThread(ExecutorService executorService) {
        super(ShutdownThread.class.getName());
        this.executorService = executorService;
        this.setDaemon(false);
    }

    @Override
    public void run() {
        synchronized (executorService) {
            if (!executorService.isShutdown()) {
                executorService.shutdown();
            }
        }
        while (true) {
            try {
                if (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                    continue;
                }
            } catch (InterruptedException e) {
                //do nothing
            }
            break;
        }
        System.out.println("Executor Service has Terminated : " + executorService.isTerminated());
    }
}
