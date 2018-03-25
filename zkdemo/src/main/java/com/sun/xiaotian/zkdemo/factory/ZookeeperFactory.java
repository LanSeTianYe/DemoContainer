package com.sun.xiaotian.zkdemo.factory;

import com.sun.xiaotian.zkdemo.constant.ZookeeperConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Zookeeper工厂类，用于创建Zookeeper
 */

public class ZookeeperFactory {

    private static final Logger logger = LogManager.getLogger(ZookeeperFactory.class);

    /**
     * 记录创建客户端的数量
     */
    private static final AtomicInteger createdClientCount = new AtomicInteger(0);

    /**
     * 创建Zookeeper连接
     *
     * @return zookeeper对象
     * @throws IOException          创建连接失败
     * @throws InterruptedException
     */
    public static ZooKeeper getZookeeper() throws IOException, InterruptedException {
        final CountDownLatch createFinishedFlag = new CountDownLatch(1);

        ZooKeeper zooKeeper = new ZooKeeper(ZookeeperConstant.HOSTS, ZookeeperConstant.SESSION_TIME, (watchedEvent) -> {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                createFinishedFlag.countDown();
                int newCount = createdClientCount.addAndGet(1);
                logger.info("创建第 " + newCount + " 个客户端。");
            } else {
                logger.warn("创建连接事件之前触发其它事件, 路径： " + watchedEvent.getPath() + " 类型： " + watchedEvent.getType());
            }
        });

        //等待客户端已经连接完成事件被触发
        createFinishedFlag.await();
        return zooKeeper;
    }
}
