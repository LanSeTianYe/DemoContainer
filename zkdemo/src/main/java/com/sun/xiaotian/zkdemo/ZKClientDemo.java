package com.sun.xiaotian.zkdemo;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.CreateMode;

/**
 * ZKClient Demo 注册的Watchers并不能保证一定被调用，调用的时候触发的事件有时候也会出错
 */
public class ZKClientDemo {

    private static final Logger logger = LogManager.getLogger(ZKClientDemo.class);

    public static void main(String[] args) {

        ZkClient zkClient = null;
        try {
            zkClient = new ZkClient("43.225.158.197", 3000);

            String path = "/Test";

            zkClient.create(path, "Value1", CreateMode.EPHEMERAL);

            zkClient.subscribeDataChanges(path, new IZkDataListener() {
                @Override
                public void handleDataChange(String dataPath, Object data) throws Exception {
                    logger.info("path: " + dataPath + " data: " + data);
                }

                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    logger.info("delete path: " + dataPath);
                }
            });

            zkClient.writeData(path, "Value2");
            zkClient.delete(path, -1);

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (zkClient != null) {
                zkClient.close();
            }
        }
    }
}
