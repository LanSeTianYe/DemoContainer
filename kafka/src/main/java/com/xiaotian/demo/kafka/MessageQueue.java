package com.xiaotian.demo.kafka;


import com.xiaotian.demo.kafka.builder.KafkaConfigBuilder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/**
 * kafka 实现消息队列
 */
public class MessageQueue {

    private final static Logger logger = LoggerFactory.getLogger(MessageQueue.class);

    private AtomicLong atomicLong = new AtomicLong(System.currentTimeMillis() / 1000);

    protected final String TOPIC_1 = "topic_2";

    private final int partition_0 = 0;
    private final int partition_1 = 1;

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.createProducer();
//        messageQueue.createConsumer();
    }

    protected void createProducer() {
        //向topic的0分区发送数据
        Thread thread1 = new Thread(() -> {
            while (true) {
                KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(KafkaConfigBuilder.getProducerConfig());
                String number = getNumber("p1");
                Future<RecordMetadata> p1 = kafkaProducer.send(new ProducerRecord<>(TOPIC_1, partition_0, number, number));
                try {
//                    p1.get();
                    TimeUnit.MILLISECONDS.sleep(50000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });

        //向topic的1分区发送数据
        Thread thread2 = new Thread(() -> {
            while (true) {
                KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(KafkaConfigBuilder.getProducerConfig());
                String number = getNumber("p2");
                Future<RecordMetadata> p2 = kafkaProducer.send(new ProducerRecord<>(TOPIC_1, partition_1, number, number));
                try {
                    p2.get();
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });

        thread1.setName("thread1");
        thread2.setName("thread2");

        thread1.start();
        thread2.start();
    }


    protected void createConsumer() {
        new Thread(() -> {
            Properties consumer = KafkaConfigBuilder.getGroup1Consumer();
            consumer.put(ConsumerConfig.CLIENT_ID_CONFIG, "client_0001");
            KafkaConsumer kafkaConsumer = new KafkaConsumer(consumer);
            kafkaConsumer.subscribe(Pattern.compile(TOPIC_1));
            while (true) {
                ConsumerRecords<String, String> poll = kafkaConsumer.poll(1);

                poll.forEach((r) -> {
                    System.out.println("consumer1_" + "partition_" + r.partition() + "value_" + r.value());
                });
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();

        new Thread(() -> {
            Properties consumer = KafkaConfigBuilder.getGroup1Consumer();
            consumer.put(ConsumerConfig.CLIENT_ID_CONFIG, "client_0002");
            KafkaConsumer kafkaConsumer = new KafkaConsumer(consumer);
            kafkaConsumer.subscribe(Pattern.compile(TOPIC_1));
            while (true) {
                ConsumerRecords<String, String> poll = kafkaConsumer.poll(1);
                poll.forEach((r) -> {
                    System.out.println("consumer2_" + "partition_" + r.partition() + "value_" + r.value());
                });
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    logger.info(e.getMessage(), e);
                }
            }
        }).start();

        new Thread(() -> {
            Properties consumer = KafkaConfigBuilder.getGroup1Consumer();
            consumer.put(ConsumerConfig.CLIENT_ID_CONFIG, "client_0003");
            KafkaConsumer kafkaConsumer = new KafkaConsumer(consumer);
            kafkaConsumer.subscribe(Pattern.compile(TOPIC_1));
            while (true) {
                ConsumerRecords<String, String> poll = kafkaConsumer.poll(1);
                poll.forEach((r) -> {
                    System.out.println("consumer3_" + "partition_" + r.partition() + "value_" + r.value());
                });
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    logger.info(e.getMessage(), e);
                }
            }
        }).start();
    }

    private String getNumber(String who) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss sss");
        String format = simpleDateFormat.format(new Date(atomicLong.getAndIncrement() * 1000));
        return who + "_" + format + format + format + format + format + format + format + format + format + format;
    }
}
