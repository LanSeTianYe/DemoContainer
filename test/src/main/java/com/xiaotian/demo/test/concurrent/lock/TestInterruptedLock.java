package com.xiaotian.demo.test.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestInterruptedLock {

    enum MethodExecStatus {START, END_RELEASE_LOCK, END_NOT_GET_LOCK}

    enum LockStatus {GET, RELEASE}

    private static final ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {

        TestInterruptedLock testInterruptedLock = new TestInterruptedLock();
        Thread thread1;
        Thread thread2;

        thread1 = new Thread(() -> testInterruptedLock.print_UseInterruptiblyLock(3));
        thread1.setName("thread-1");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread2 = new Thread(() -> testInterruptedLock.print_UseInterruptiblyLock(1));
        thread2.setName("thread-2");
        thread2.start();
        thread2.interrupt();
        TimeUnit.SECONDS.sleep(4);
        testInterruptedLock.printBlankLine();

        thread1 = new Thread(() -> testInterruptedLock.print_UseTryLock(3));
        thread1.setName("thread-1");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread2 = new Thread(() -> testInterruptedLock.print_UseTryLock(1));
        thread2.setName("thread-2");
        thread2.start();

        thread2.interrupt();
        TimeUnit.SECONDS.sleep(4);
        testInterruptedLock.printBlankLine();

        thread1 = new Thread(() -> testInterruptedLock.print_UseLock(3));
        thread1.setName("thread-1");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);

        thread2 = new Thread(() -> testInterruptedLock.print_UseLock(1));
        thread2.setName("thread-2");
        thread2.start();
        thread2.interrupt();
        TimeUnit.SECONDS.sleep(4);
    }

    private void print_UseInterruptiblyLock(int sleepSeconds) {
        String methodName = getMethodName(getMethodStackDeep());
        printMethodExecStatus(MethodExecStatus.START, methodName);
        try {
            //获取锁，获取过程中可以响应中断操作
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            printException(e, methodName);
            printMethodExecStatus(MethodExecStatus.END_NOT_GET_LOCK, methodName);
            return;
        }
        executeWithLock(lock, sleepSeconds, methodName);
    }

    private void print_UseTryLock(int sleepSeconds) {
        String methodName = getMethodName(getMethodStackDeep());
        printMethodExecStatus(MethodExecStatus.START, methodName);
        //尝试获取，获取不到立即返回false
        if (lock.tryLock()) {
            executeWithLock(lock, sleepSeconds, methodName);
        } else {
            printMethodExecStatus(MethodExecStatus.END_NOT_GET_LOCK, methodName);
        }
    }

    private void print_UseLock(int sleepSeconds) {
        String methodName = getMethodName(getMethodStackDeep());
        printMethodExecStatus(MethodExecStatus.START, methodName);
        //一直等待直到获取到锁
        lock.lock();
        executeWithLock(lock, sleepSeconds, methodName);
    }

    private void executeWithLock(Lock lock, int sleepSeconds, String methodName) {
        try {
            printLockStatus(LockStatus.GET, methodName);
            TimeUnit.SECONDS.sleep(sleepSeconds);
            printLockStatus(LockStatus.RELEASE, methodName);
        } catch (InterruptedException e) {
            printException(e, methodName);
        } finally {
            lock.unlock();
            printMethodExecStatus(MethodExecStatus.END_RELEASE_LOCK, methodName);
        }
    }

    public void printBlankLine() {
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void printMethodExecStatus(MethodExecStatus status, String methodName) {
        System.out.println(String.format("[%s]--[%s] %s", methodName, getThreadName(), status));
    }

    private void printLockStatus(LockStatus status, String methodName) {
        System.out.println(String.format("[%s]--[%s] %s", methodName, getThreadName(), status));
    }

    private void printException(Exception e, String methodName) {
        System.out.println(String.format("[%s]--[%s] %s", methodName, getThreadName(), e.getMessage()));
    }

    private int getMethodStackDeep() {
        return Thread.currentThread().getStackTrace().length - 2;
    }

    private String getMethodName(int deep) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //倒数deep个 正数 stackTrace.length - deep + 1 个，数组下标减一
        return stackTrace[(stackTrace.length - deep + 1) - 1].getMethodName();
    }

    private String getThreadName() {
        return Thread.currentThread().getName();
    }
}
