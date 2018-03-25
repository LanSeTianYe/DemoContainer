package com.sun.xiaotian.demo.zookeeper.config;

import com.sun.xiaotian.demo.zookeeper.constant.ZookeeperConstant;
import com.sun.xiaotian.demo.zookeeper.factory.ZookeeperFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 分布式客户端
 */

public class DistributeClient {

    private final static Logger logger = LogManager.getLogger(DistributeClient.class);

    private ZooKeeper zooKeeper;

    /**
     * 运行客户端
     *
     * @throws InterruptedException
     * @throws IOException
     * @throws KeeperException
     */
    public void start() throws InterruptedException, IOException, KeeperException {
        getConfigInfo();
    }

    private ZooKeeper getZookeeper() throws IOException, InterruptedException {
        return ZookeeperFactory.getZookeeper();
    }

    /**
     * 获取配置信息
     */
    private void getConfigInfo() throws IOException, InterruptedException, KeeperException {
        if (zooKeeper == null) {
            zooKeeper = getZookeeper();
        }

        String parentPath = ZookeeperConstant.DISTRIBUTE_CONFIG_PATH;

        for (KeyConstant keyConstant : KeyConstant.values()) {
            String path = parentPath + "/" + keyConstant.getText();
            Stat nodeStat = zooKeeper.exists(path, false);
            if (nodeStat == null) {
                logger.error("配置信息节点不存在, 不存在节点：" + path);
                throw new RuntimeException("配置信息节点不存在");
            } else {
                byte[] data = zooKeeper.getData(path, new GetNewConfigInfoWatcher(), null);
                logger.info(this.hashCode() + "配置信息, path: " + path + " value: " + new String(data, ZookeeperConstant.UTF_8));
            }
        }
    }

    private class GetNewConfigInfoWatcher implements Watcher {
        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeDataChanged) {
                try {
                    getConfigInfoByPath(event.getPath());
                } catch (Exception e) {
                    logger.error("获取更新的配置信息出错", e);
                }
            }
        }
    }

    private void getConfigInfoByPath(String path) throws KeeperException, InterruptedException, UnsupportedEncodingException {
        byte[] data = zooKeeper.getData(path, new GetNewConfigInfoWatcher(), null);
        logger.info(this.hashCode() + " 获取配置信息 path:value " + path + ":" + new String(data, ZookeeperConstant.UTF_8));
    }
}
