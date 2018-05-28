package com.sun.xiaotian.demo.kafka.stream.util;

import com.sun.xiaotian.demo.kafka.builder.KafkaConfigBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TextDataWriter implements TopicDataWriter {

    private final static Logger logger = LoggerFactory.getLogger(TextDataWriter.class);

    private final static String text = "The New York Times uses Apache Kafka and the Kafka Streams API" +
            " to store and distribute, in real-time, published content to the various applications " +
            "and systems that make it available to the readers.";

    @Override
    public void producer(String topic, long millSeconds) {

        Thread thread = new Thread(() -> {
            int times = 0;
            Properties producerConfig = KafkaConfigBuilder.getProducerConfig();
            KafkaProducer<String, String> producer = new KafkaProducer(producerConfig);
            long startTime = System.currentTimeMillis();
            long currTime = System.currentTimeMillis();

            while (currTime - startTime < millSeconds) {
                try {
                    producer.send(new ProducerRecord<>(topic, String.valueOf(times), text));
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
                currTime = System.currentTimeMillis();
            }
            producer.close();
        });

        thread.setName("thread" + topic);
        thread.setDaemon(true);
        thread.start();
    }
}
