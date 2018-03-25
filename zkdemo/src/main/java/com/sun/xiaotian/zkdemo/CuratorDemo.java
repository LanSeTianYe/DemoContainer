package com.sun.xiaotian.zkdemo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

public class CuratorDemo {

    private final static Logger logger = LogManager.getLogger(CuratorDemo.class);

    public static void main(String[] args) throws Exception {

        String lockPath = "/lock";

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(500, 10);

        //分布式锁
        CuratorFramework client1 = CuratorFrameworkFactory.newClient("43.225.158.197", retryPolicy);
        client1.start();
        InterProcessMutex lock = new InterProcessMutex(client1, lockPath);

        if (lock.acquire(3, TimeUnit.SECONDS)) {
            try {
                logger.info("getLock");
            } finally {
                lock.release();
            }
        }

        CuratorFramework client2 = CuratorFrameworkFactory
                .builder()
                .connectString("43.225.158.197")
                .retryPolicy(retryPolicy)
                .build();
        client2.start();
        client2.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/path2", "NULL".getBytes());

        client1.close();
        client2.close();
    }

}
