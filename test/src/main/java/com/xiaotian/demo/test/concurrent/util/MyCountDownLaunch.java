package com.xiaotian.demo.test.concurrent.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

public class MyCountDownLaunch {

    private final Sync sync = new Sync();

    public MyCountDownLaunch(int count) {
        sync.setCount(count);
    }

    public void await() {
        sync.acquireShared(1);
    }

    public void release() {
        sync.releaseShared(1);
    }

    private final class Sync extends AbstractQueuedLongSynchronizer {

        private static final long serialVersionUID = 3619313422345551601L;

        private void setCount(int count) {
            super.setState(count);
        }

        @Override
        protected boolean tryReleaseShared(long arg) {
            //防止 a-b 之间其它线程改变状态
            for (; ; ) {
                //a
                long currState = getState();
                if (currState == 0) {
                    return false;
                }
                long nextState = currState - 1;
                //b
                if (compareAndSetState(currState, nextState)) {
                    return nextState == 0;
                }
            }
        }

        @Override
        /**
         * 状态：
         * 1 共享模式：所有等待线程执行
         * 0 独占模式：只有一个线程可以执行
         * 1 获取失败：线程键入队列
         */
        protected long tryAcquireShared(long arg) {
            return getState() == 0 ? 1 : -1;
        }
    }

    public static void main(String[] args) {
        int cpuCoreNumber = Runtime.getRuntime().availableProcessors();
        System.out.println(String.format("available core: %s", cpuCoreNumber));
        MyCountDownLaunch myCountDownLaunch = new MyCountDownLaunch(cpuCoreNumber);

        for (long i = 0; i < cpuCoreNumber; i++) {
            long sleepSecond = i;
            CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(sleepSecond);
                    System.out.println(String.format("thread: %s, sleepSecond: %s", Thread.currentThread().getId(), sleepSecond));
                    myCountDownLaunch.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        myCountDownLaunch.await();
        System.out.println("success");
    }
}
