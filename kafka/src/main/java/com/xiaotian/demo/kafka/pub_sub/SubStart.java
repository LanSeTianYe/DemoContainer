package com.xiaotian.demo.kafka.pub_sub;

import com.xiaotian.demo.kafka.messagequeue.Consumer;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午17:33
 */
public class SubStart {

    public static void main(String[] args) {
        String topicName = "test_sub_pub";

        Consumer consumer = new Consumer();
        String group1 = "pub_sub_group_7";
        String group2 = "pub_sub_group_8";

        consumer.consumerFromLatest(group1, topicName);

        consumer.consumerFromLatest(group2, topicName);
    }
}
