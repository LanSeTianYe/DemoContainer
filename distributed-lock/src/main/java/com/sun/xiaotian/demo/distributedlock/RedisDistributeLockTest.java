package com.sun.xiaotian.demo.distributedlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RedisDistributeLockTest {

    private final static Logger logger = LoggerFactory.getLogger(RedisDistributeLockTest.class);

    public static void main(String[] args) throws InterruptedException {

        //线程数量
        int threadSize = 100;

        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch downLatch = new CountDownLatch(threadSize);
        Random random = new Random(23);

        //模拟异步时要执行的互斥资源操作（依据 ++操作不是原子操作）
        Count count = new Count(0);

        //线程同步进行
        for (int i = 0; i < threadSize; i++) {
            LockInfo lockInfo = new LockInfo();
            lockInfo.setResourcesId("lock_resources1");

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    RedisDistributedLock redisDistributedLock = new RedisDistributedLock();
                    lockInfo.setThreadId(Thread.currentThread().getId());
                    lockInfo.setExpireDate(2);
                    //取得锁
                    redisDistributedLock.getLock(lockInfo);
                    //执行一些操作
                    count.add();
                    try {
                        TimeUnit.MICROSECONDS.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        downLatch.countDown();
                        boolean result = redisDistributedLock.releaseLock(lockInfo);
                        if (!result) {
                            logger.info("释放锁失败: " + lockInfo.getThreadId());
                        }
                    }
                }
            });
        }

        downLatch.await();
        System.out.println("count == threadSize ? " + (count.getCount() == threadSize));
        executorService.shutdownNow();
    }
}

class Count {

    private int count = 0;

    public Count(int count) {
        this.count = count;
    }

    public void add() {
        count ++;
    }

    public int getCount() {
        return count;
    }
}
