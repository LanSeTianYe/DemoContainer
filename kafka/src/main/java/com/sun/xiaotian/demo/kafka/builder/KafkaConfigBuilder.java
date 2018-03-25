package com.sun.xiaotian.demo.kafka.builder;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public abstract class KafkaConfigBuilder {

    /**
     * 指定kafka集群中的任意一台机器，可以自动发现集群中的其他机器
     */
    public static final String kafkaServer = "127.0.0.1:9093";


    public static Properties getProducerConfig() {
        Properties producerProperties = new Properties();
        producerProperties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        producerProperties.put(ProducerConfig.ACKS_CONFIG, "all");
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, 0);
        producerProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        producerProperties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        producerProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return producerProperties;
    }

    public static Properties getGroup1Consumer() {
        Properties consumerProperties = new Properties();
        consumerProperties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        consumerProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return consumerProperties;
    }

    public static Properties getGroup2Consumer() {
        Properties consumerProperties = new Properties();
        consumerProperties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");
        consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        consumerProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return consumerProperties;
    }

}
