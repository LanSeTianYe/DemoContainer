package com.xiaotian.demo.kafka.stream.util;


import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.function.Consumer;

/**
 * 从kafka topic中读取数据
 */
public interface TopicDataReader {

    /**
     * 读取数据
     *
     * @param topic topic的名字
     */
    public <K, V> void consume(String topic, Consumer<ConsumerRecord<K, V>> recordConsumer, String kDs, String vDs);
}
