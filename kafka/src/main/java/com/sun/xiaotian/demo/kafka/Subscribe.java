package com.sun.xiaotian.demo.kafka;


import com.sun.xiaotian.demo.kafka.builder.KafkaConfigBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/**
 * kafka 实现消息订阅
 */
public class Subscribe extends MessageQueue {

    private final static Logger logger = LoggerFactory.getLogger(Subscribe.class);

    public static void main(String[] args) {
        Subscribe subscribe = new Subscribe();
        subscribe.createProducer();
        subscribe.createConsumer();
        subscribe.createConsumer_group2();
    }


    protected void createConsumer_group2() {
        //每一个被订阅的topic里面的数据都会完整的发送给每一个消费者组
        //consumer1和consumer2获得的信息的和是完整的信息
        //consumer3获取的信息是完整的信息
        new Thread(() -> {
            KafkaConsumer kafkaConsumer = new KafkaConsumer(KafkaConfigBuilder.getGroup2Consumer());
            kafkaConsumer.subscribe(Pattern.compile(TOPIC_1));
            while (true) {
                ConsumerRecords<String, String> poll = kafkaConsumer.poll(1);
                poll.forEach((r) -> {
                    System.out.println("consumer3_" + "partition_" + r.partition() + "value_" + r.value());
                });
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();
    }
}
