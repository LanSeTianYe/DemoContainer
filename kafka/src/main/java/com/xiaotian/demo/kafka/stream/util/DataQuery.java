package com.sun.xiaotian.demo.kafka.stream.util;

import org.apache.kafka.streams.KafkaStreams;

public interface DataQuery<K, V> {

    /**
     * 查询Store里存的数据
     *
     * @param streams   流
     * @param storeName store的名字
     */
    public void query(KafkaStreams streams, String storeName);
}
