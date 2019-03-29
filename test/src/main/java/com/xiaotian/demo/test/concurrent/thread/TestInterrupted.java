package com.xiaotian.demo.test.concurrent.thread;

import java.util.concurrent.TimeUnit;

public class TestInterrupted {

    public static void main(String[] args) throws InterruptedException {
        EmptyThread emptyThread = new EmptyThread();
        emptyThread.start();
        TimeUnit.SECONDS.sleep(1);
        emptyThread.interrupt();
        TimeUnit.SECONDS.sleep(1);
    }

}

class EmptyThread extends Thread {

    private boolean interrupted = false;

    public EmptyThread() {
        super("Empty Thread");
        setDaemon(false);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            interrupted = true;
            System.out.println("Thread Interrupted");
        }
    }
}



