package com.sun.xiaotian.demo.zookeeper.config;

import com.sun.xiaotian.demo.zookeeper.constant.ZookeeperConstant;
import com.sun.xiaotian.demo.zookeeper.factory.ZookeeperFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 初始化配置信息
 */

public class UpdateConfigThread extends Thread {

    private static final Logger logger = LogManager.getLogger(UpdateConfigThread.class);

    private final Random random = new Random(37);

    //记录更新配置信息的次数
    private final AtomicInteger updateTimes = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            update();
        } catch (Exception e) {
            logger.error("更新配置信息出错！", e);
        }
    }

    /**
     * 初始化配置信息
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void update() throws KeeperException, InterruptedException, IOException {
        ZooKeeper zooKeeper = ZookeeperFactory.getZookeeper();
        while (true) {
            int times = updateTimes.addAndGet(1);
            logger.info("第 " + times + " 次更新配置信息!");
            for (KeyConstant keyConstant : KeyConstant.values()) {
                String path = ZookeeperConstant.DISTRIBUTE_CONFIG_PATH + "/" + keyConstant.getText();
                byte[] data = (random.nextInt(1000) + "").getBytes(ZookeeperConstant.UTF_8);
                zooKeeper.setData(path, data, -1);
            }
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
