package com.xiaotian.demo.kafka;

import com.xiaotian.demo.kafka.factory.ConfigFactory;
import org.apache.kafka.clients.admin.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午10:54
 */

public class AdminClientDemo {

    private final static Logger logger = LogManager.getLogger(AdminClientDemo.class);
    private final AtomicInteger count = new AtomicInteger(0);

    /**
     * 创建话题（Topic）
     *
     * @param name              话题的名字
     * @param numPartitions     分区数
     * @param replicationFactor 复制因子的个数
     */
    public boolean createTopic(String name, int numPartitions, short replicationFactor) {
        int startNumber = count.get();
        try (AdminClient adminClient = AdminClient.create(ConfigFactory.getAdminClientConfig())) {
            NewTopic newTopic = new NewTopic(name, numPartitions, replicationFactor);
            CreateTopicsResult topics = adminClient.createTopics(Collections.singleton(newTopic));
            topics.all().whenComplete(this::handelException);
        }
        return startNumber == count.get();
    }

    public boolean existsTopic(String name) {
        try (AdminClient adminClient = AdminClient.create(ConfigFactory.getAdminClientConfig())) {
            ListTopicsResult topics = adminClient.listTopics();
            try {
                Set<String> topicNames = topics.names().get();
                for (String topicName : topicNames) {
                    if (topicName.equalsIgnoreCase(name)) {
                        return true;
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 删除分区
     *
     * @param name 分区名字
     * @return
     */
    public boolean deleteTopic(String name) {
        int startNumber = count.get();
        try (AdminClient adminClient = AdminClient.create(ConfigFactory.getAdminClientConfig())) {
            DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singleton(name));
            try {
                deleteTopicsResult.all().get();
            } catch (Exception e) {
                count.getAndIncrement();
                logger.info(e.getMessage(), e);
            }
            logger.info("delete success: " + name);
        }
        return startNumber == count.get();
    }

    /**
     * 获取topic列表
     */
    public void listTopic() {
        try (AdminClient adminClient = AdminClient.create(ConfigFactory.getAdminClientConfig())) {
            ListTopicsResult listTopicsResult = adminClient.listTopics();
            listTopicsResult.names().whenComplete((topics, exception) -> {
                if (null != exception) {
                    logger.error(exception.getMessage(), exception);
                } else {
                    topics.forEach(logger::info);
                }
            });
        }
    }

    private <A extends Object, B extends Throwable> void handelException(A o, B e) {
        if (null != e) {
            count.getAndIncrement();
            logger.error(e.getMessage(), e);
        }
    }
}
