package com.sun.xiaotian.demo.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

public class TestEphemeralNode {

    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(500, 10);

    public static void main(String[] args) throws Exception {
        TestEphemeralNode testEphemeralNode = new TestEphemeralNode();
        testEphemeralNode.testAddEphemeralSequentialNode();
        TimeUnit.SECONDS.sleep(10);
    }

    private void testAddEphemeralNode() throws Exception {
        CuratorFramework client2 = CuratorFrameworkFactory
                .builder()
                .connectString("43.225.158.197")
                .retryPolicy(retryPolicy)
                .build();
        client2.start();
        client2.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test", "NULL".getBytes());
        // Exception KeeperException$NoChildrenForEphemeralsException
        // 临时节点下面不能创建子节点
        client2.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test/12", "NULL".getBytes());
    }

    private void testAddEphemeralSequentialNode() throws Exception {
        CuratorFramework client2 = CuratorFrameworkFactory
                .builder()
                .connectString("43.225.158.197")
                .retryPolicy(retryPolicy)
                .build();
        client2.start();
        client2.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/test", "NULL".getBytes());
        // 临时顺序节点
        client2.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test/aa", "NULL".getBytes());
        client2.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test/aa", "NULL".getBytes());
        client2.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test/aa", "NULL".getBytes());
    }
}
