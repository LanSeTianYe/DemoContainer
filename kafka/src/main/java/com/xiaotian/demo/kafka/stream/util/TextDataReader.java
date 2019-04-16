package com.sun.xiaotian.demo.kafka.stream.util;

import com.sun.xiaotian.demo.kafka.builder.KafkaConfigBuilder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class TextDataReader implements TopicDataReader {

    
    private final static Logger logger = LoggerFactory.getLogger(TextDataReader.class);

    @Override
    public <K, V> void consume(String topic, Consumer<ConsumerRecord<K, V>> recordConsumer, String kDs, String vDs) {

        Thread thread = new Thread(() -> {
            Properties consumerConfig = KafkaConfigBuilder.getGroup1Consumer();
            consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kDs);
            consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, vDs);

            KafkaConsumer<K, V> consumer = new KafkaConsumer(consumerConfig);
            consumer.subscribe(Pattern.compile(topic));
            consumer.seekToEnd(consumer.assignment());

            while (true) {
                ConsumerRecords<K, V> records = consumer.poll(0);
                for (ConsumerRecord<K, V> record : records) {
                    recordConsumer.accept(record);
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });

        thread.setName("thread_" + topic);
        thread.setDaemon(true);

        thread.start();
    }
}
