package com.xiaotian.demo.test.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * wait 会释放当前线程获取的锁，当收到通知后其中一个wait的重新获取锁
 */
public class TestAwaitSingal {

    public static void main(String[] args) throws InterruptedException {
        Door door = new Door();

        int num = 1;

        for (int i = 0; i < 5; i++) {
            ThreadFactory.createThread("thread-o-" + num++, () -> {
                try {
                    while (true) {
                        door.open();
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        num = 1;
        for (int i = 0; i < 50; i++) {
            ThreadFactory.createThread("thread-c-" + num++, () -> {
                try {
                    while (true) {
                        door.close();
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        TimeUnit.SECONDS.sleep(10);
    }


    enum DoorStatus {CLOSE, OPEN}

    static class Door {

        private ReentrantLock lock = new ReentrantLock();

        private Condition open = lock.newCondition();
        private Condition close = lock.newCondition();

        private DoorStatus doorStatus = DoorStatus.CLOSE;

        public void open() throws InterruptedException {
            lock.lock();
            try {
                printBeginOpenInfo();
                while (doorStatus == DoorStatus.OPEN) {
                    printWaitInfo();
                    close.await();
                    printWakeUpInfo();
                }
                this.doorStatus = DoorStatus.OPEN;
                TimeUnit.SECONDS.sleep(1);
                open.signal();
            } finally {
                lock.unlock();
            }
        }

        public synchronized void close() throws InterruptedException {
            lock.lock();
            try {
                printBeginClose();
                while (doorStatus == DoorStatus.CLOSE) {
                    printWaitInfo();
                    open.await();
                    printWakeUpInfo();
                }
                this.doorStatus = DoorStatus.CLOSE;
                TimeUnit.SECONDS.sleep(1);
                printNotifyInfo();
                close.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    private static void printWaitInfo() {
        printThreadInfo("wait");
    }

    private static void printWakeUpInfo() {
        printThreadInfo("wake up");
    }


    private static void printOpenInfo() {
        printThreadInfo("open");
    }

    private static void printCloseInfo() {
        printThreadInfo("close");
    }

    private static void printNotifyInfo() {
        printThreadInfo("notify");
    }

    private static void printBeginOpenInfo() {
        printThreadInfo("begin open");
    }

    private static void printBeginClose() {
        printThreadInfo("begin close");
    }

    private static void printThreadInfo(String description) {
        System.out.println(String.format("%s %s %s", System.currentTimeMillis() / 1000, Thread.currentThread().getName(), description));
    }

    static class ThreadFactory {
        public static Thread createThread(String threadName, Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(threadName);
            thread.start();
            return thread;
        }
    }
}
