package com.xiaotian.demo.kafka.messagequeue;

import com.xiaotian.demo.kafka.factory.ConfigFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Properties;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午12:19
 */
public class Consumer {

    private final static Logger logger = LogManager.getLogger(Consumer.class);

    public void consumerFromBegin(String groupName,String topicName) {
        new Thread(() -> {
            try{
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(ConfigFactory.getUnAutoCommitConsumerConfig(groupName));
                consumer.subscribe(Collections.singleton(topicName));
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    records.forEach(record -> {
                        logger.info(String.format("threadId: %s, topic: %s, partition: %s, offset: %s, key: %s, value: %s", Thread.currentThread().getId(), record.topic(), record.partition(), record.offset(),record.key(), record.value()));
                    });
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }).start();
    }

    public void consumerFromOffset(String groupName, String topicName, Long offset){
        new Thread(() -> {
            try{
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(ConfigFactory.getUnAutoCommitConsumerConfig(groupName));
                consumer.subscribe(Collections.singleton(topicName));
                consumer.poll(100);
                consumer.seek(new TopicPartition(topicName, 0), offset);
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    records.forEach(record -> {
                        logger.info(String.format("threadId: %s, topic: %s, partition: %s, offset: %s, key: %s, value: %s", Thread.currentThread().getId(), record.topic(), record.partition(), record.offset(),record.key(), record.value()));
                    });
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }).start();
    }

    public void consumer(String groupName, String topicName, Class<? extends Deserializer> keyDeserializer, Class<? extends Deserializer> valueDeserializer) {
        new Thread(() -> {
            try{
                Properties consumerConfig = ConfigFactory.getAutoCommitConsumerConfig(groupName);
                consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer.getCanonicalName());
                consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer.getCanonicalName());
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerConfig);
                consumer.subscribe(Collections.singleton(topicName));
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    records.forEach(record -> {
                        logger.info(String.format("threadId: %s, topic: %s, partition: %s, offset: %s, key:%s, value: %s", Thread.currentThread().getId(), record.topic(), record.partition(), record.offset(),record.key(), record.value()));
                    });
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }).start();
    }

    public void consumerFromLatest(String groupName,String topicName) {
        new Thread(() -> {
            try{
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(ConfigFactory.getAutoCommitConsumerConfig(groupName));
                consumer.subscribe(Collections.singleton(topicName));
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    records.forEach(record -> {
                        logger.info(String.format("threadId: %s, topic: %s, partition: %s, offset: %s, key:%s, value: %s", Thread.currentThread().getId(), record.topic(), record.partition(), record.offset(),record.key(), record.value()));
                    });
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }).start();
    }

    public void consumerFromLatest_Long(String groupName,String topicName) {
        new Thread(() -> {
            try{
                Properties autoCommitConsumerConfig = ConfigFactory.getAutoCommitConsumerConfig(groupName);
                autoCommitConsumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.LongDeserializer");
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(autoCommitConsumerConfig);
                consumer.subscribe(Collections.singleton(topicName));
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    records.forEach(record -> {
                        logger.info(String.format("threadId: %s, topic: %s, partition: %s, offset: %s, key: %s, value: %s", Thread.currentThread().getId(), record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                    });
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }).start();
    }



    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        String topicName = "test_producer_consumer";

//        String groupName = "group_003";
//        consumer.consumerFromBegin(groupName, topicName);
//        consumer.consumerFromBegin(groupName, topicName);
//        consumer.consumerFromBegin(groupName, topicName);
//        consumer.consumerFromBegin(groupName, topicName);

        String groupName = "group_004";
        consumer.consumerFromLatest(groupName, topicName);
        consumer.consumerFromLatest(groupName, topicName);
        consumer.consumerFromLatest(groupName, topicName);
    }
}
