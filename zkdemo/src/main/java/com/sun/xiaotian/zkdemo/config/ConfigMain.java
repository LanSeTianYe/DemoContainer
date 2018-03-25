package com.sun.xiaotian.zkdemo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * 配置文件使用启动类
 */

public class ConfigMain {

    private static final Logger logger = LogManager.getLogger(ConfigMain.class);

    private static final int clientCount = 10;

    public static void main(String[] args) throws InterruptedException {
        ConfigMain configMain = new ConfigMain();
        configMain.start();
        TimeUnit.SECONDS.sleep(1000);
    }

    private void start() {
        try {
            initConfigInfo();
        } catch (Exception e) {
            logger.error("初始化配置信息失败!", e);
        }
        initClient();
        updateConfig();
    }

    //初始化读取配置信息的客户端
    private void initClient() {
        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        for (int i = 0; i < clientCount; i++) {
            executorService.submit(() -> {
                try {
                    DistributeClient distributeClient = new DistributeClient();
                    distributeClient.start();
                } catch (IOException e) {
                    logger.error("创建连接失败!" + e.getMessage(), e);
                    throw new RuntimeException("初始化客户端出错", e);
                } catch (InterruptedException e) {
                    logger.error("zookeeper 连接中断!" + e.getMessage(), e);
                    throw new RuntimeException("初始化客户端出错", e);
                } catch (KeeperException e) {
                    logger.error(e.getMessage(), e);
                    throw new RuntimeException("初始化客户端出错", e);
                }
            });
        }
    }

    //初始化配置文件
    private void initConfigInfo() throws ExecutionException, InterruptedException {
        InitConfigCallable initConfigCallable = new InitConfigCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(initConfigCallable);
        //等待初始化执行完成
        submit.get();
    }

    //更新配置信息
    private void updateConfig() {
        UpdateConfigThread updateThread = new UpdateConfigThread();
        updateThread.setDaemon(true);
        updateThread.start();
    }
}
