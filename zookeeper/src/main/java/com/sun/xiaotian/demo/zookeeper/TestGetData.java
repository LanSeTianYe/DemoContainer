package com.sun.xiaotian.demo.zookeeper;

import org.apache.zookeeper.ZooKeeper;

public class TestGetData {

    public static void main(String[] args) throws InterruptedException {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("43.225.158.197", 3000, null);
            Object readData = zooKeeper.getData("/dubbo", true, null);
            System.out.println(readData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zooKeeper.close();
        }
    }
}
