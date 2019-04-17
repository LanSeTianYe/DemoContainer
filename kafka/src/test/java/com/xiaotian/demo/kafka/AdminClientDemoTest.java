package com.xiaotian.demo.kafka;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午11:16
 */

public class AdminClientDemoTest {

    private AdminClientDemo createTopic = new AdminClientDemo();

    private String topicName = "test_producer_consumer";

    @Test
    public void testCreateTopic() {
        short  replicationFactor = 3;
        assertTrue(createTopic.createTopic(topicName, 3, replicationFactor));
    }

    @Test
    public void testCreateTopic_OverLimit() {
        short  replicationFactor = 4;
        assertFalse(createTopic.createTopic(topicName, 3, replicationFactor));
    }

    @Test
    public void testDeleteTopic() {
        assertTrue(createTopic.deleteTopic(String.valueOf(topicName)));
    }

    @Test
    public void testListTopic() {
        createTopic.listTopic();
    }
}