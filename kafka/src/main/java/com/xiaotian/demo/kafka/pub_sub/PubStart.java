package com.xiaotian.demo.kafka.pub_sub;

import com.xiaotian.demo.kafka.AdminClientDemo;
import com.xiaotian.demo.kafka.messagequeue.Producer;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午17:33
 */
public class PubStart {

    public static void main(String[] args) throws InterruptedException {
        String topicName = "test_sub_pub";

        AdminClientDemo adminClientDemo = new AdminClientDemo();
        if (!adminClientDemo.existsTopic(topicName)) {
            adminClientDemo.createTopic(topicName, 3, Short.valueOf("3"));
        }

        Producer producer = new Producer();
        producer.producerData(topicName, 0);
        producer.producerData(topicName, 1);
        producer.producerData(topicName, 2);
    }

}
