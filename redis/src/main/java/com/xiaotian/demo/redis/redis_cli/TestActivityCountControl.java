package com.xiaotian.demo.redis.redis_cli;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 活动数量控制
 */
public class TestActivityCountControl {

    private final static Logger logger = LogManager.getLogger(TestActivityCountControl.class);

    private static ReentrantLock listLock = new ReentrantLock();

    private static final int clientCount = 100;

    private static final int clientPeopleCount = 10;

    private static final int activityControlCount = 10;

    private static final AtomicInteger userNum = new AtomicInteger(0);

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(clientCount);
    private static final CountDownLatch countDownLatch = new CountDownLatch(activityControlCount);

    private static final List<User> successUser = new ArrayList<>();

    private static final ActivityCountControl activityCountControl = new ActivityCountControl();

    public static void main(String[] args) throws InterruptedException {
        TestActivityCountControl testActivityCountControl = new TestActivityCountControl();
        //开始活动
        Activity activity = new Activity("yi_yuan_shi_jia", activityControlCount);
        activityCountControl.startActivity(activity.name, activity.count);

        //模拟参与活动
        for (int i = 0; i < clientCount; i++) {
            testActivityCountControl.mockActivity(activity);
        }

        //输出结果
        countDownLatch.await();
        logger.info("success user count: " + successUser.size());
        successUser.forEach(user -> {
            logger.info("success user: " + user);
        });
    }


    /**
     * 模拟参与活动
     * @param activity
     * @throws InterruptedException
     */
    public void mockActivity(Activity activity) {
        Thread thread = new Thread(() -> {
            List<CompletableFuture<Void>> futureList = new ArrayList<>();
            List<User> userList = new ArrayList<>();
            List<Boolean> results = new ArrayList<>();

            for (int i = 0; i < clientPeopleCount; i++) {
                userList.add(User.of("people_" + userNum.incrementAndGet()));
            }

            try {
                //保证多个客户端同时开始
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

            userList.forEach(user -> {
                futureList.add(CompletableFuture.runAsync(() -> {
                    logger.info(String.format("thread: %s, %s participateActivity ... start", Thread.currentThread().getId(), user.toString()));
                    Condition condition = listLock.newCondition();
                    listLock.lock();
                    try {
                        boolean success = activityCountControl.participateActivity(activity.name);
                        if(success) {
                            successUser.add(user);
                            countDownLatch.countDown();
                        }
                        results.add(success);
                    } finally {
                        listLock.unlock();
                    }
                    logger.info(String.format("thread: %s, %s participateActivity ... end", Thread.currentThread().getId(), user.toString()));
                }));
            });

            CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
            long successCount = results.stream().filter(r -> r).count();
            logger.info(String.format("listSize: %s, successCount: %s ", results.size(), successCount));
        });
        thread.start();
    }

    /**
     * 活动
     */
    static class Activity implements Serializable {

        private static final long serialVersionUID = 8140261320785925730L;

        private final String name;
        private final int count;

        Activity(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }

    /**
     * 用户
     */
    static class User implements Serializable {

        private final String name;

        private User(String name) {
            this.name = name;
        }

        public static User of(String name) {
            return new User(name);
        }

        @Override
        public String toString() {
            return "People{" + "name='" + name + '\'' + '}';
        }
    }
}

