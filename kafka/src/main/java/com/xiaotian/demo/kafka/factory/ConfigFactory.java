package com.xiaotian.demo.kafka.factory;

import com.xiaotian.demo.kafka.constant.KafkaConfigConstant;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午11:03
 */

public class ConfigFactory {

    public static Properties getAdminClientConfig() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfigConstant.BOOTSTRAP_SERVERS_CONFIG);
        properties.put(AdminClientConfig.CLIENT_ID_CONFIG, "admin_client_001");
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);
        return properties;
    }

    public static Properties getProducerConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfigConstant.BOOTSTRAP_SERVERS_CONFIG);
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, 0);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 10000);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

    public static Properties getAutoCommitConsumerConfig(String groupName) {
        Properties consumerProperties = getCommonConsumerConfig();
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        return consumerProperties;
    }

    public static Properties getUnAutoCommitConsumerConfig(String groupName) {
        Properties consumerProperties = getCommonConsumerConfig();
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        return consumerProperties;
    }

    public static Properties getStreamProperties(String applicationId) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfigConstant.BOOTSTRAP_SERVERS_CONFIG);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return properties;
    }

    private static Properties getCommonConsumerConfig() {
        Properties consumerProperties = new Properties();
        consumerProperties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, KafkaConfigConstant.BOOTSTRAP_SERVERS_CONFIG);
        consumerProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return consumerProperties;
    }
}
