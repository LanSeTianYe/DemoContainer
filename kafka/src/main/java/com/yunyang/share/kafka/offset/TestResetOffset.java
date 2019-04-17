package com.yunyang.share.kafka.offset;

import com.yunyang.share.kafka.messagequeue.Consumer;
import com.yunyang.share.kafka.messagequeue.Producer;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年06月04日 下午10:51
 */

public class TestResetOffset {

    public static void main(String[] args) throws InterruptedException {

        String topicName = "test_reset_offset";

        Producer producer = new Producer();
        producer.producerData(topicName, 0);

        Consumer consumer = new Consumer();
        consumer.consumerFromOffset("test_reset_offset", topicName, 10L);
    }
}
