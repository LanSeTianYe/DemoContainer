package com.xiaotian.demo.kafka.pub_sub;

import com.xiaotian.demo.kafka.messagequeue.Producer;
import com.xiaotian.demo.kafka.AdminClientDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午17:33
 */
public class PubStart {

    public static void main(String[] args) throws InterruptedException {
        String topicName = "test_sub_pub";

        AdminClientDemo adminClientDemo = new AdminClientDemo();
        boolean b = adminClientDemo.deleteTopic(topicName);

        TimeUnit.SECONDS.sleep(1);
        adminClientDemo.createTopic(topicName, 3,  Short.valueOf("3"));

        Producer producer = new Producer();
        producer.producerData(topicName, 0);
        producer.producerData(topicName, 1);
        producer.producerData(topicName, 2);
    }

}
