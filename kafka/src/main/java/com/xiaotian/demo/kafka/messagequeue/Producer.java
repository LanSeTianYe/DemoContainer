package com.xiaotian.demo.kafka.messagequeue;

import com.xiaotian.demo.kafka.factory.ConfigFactory;
import com.xiaotian.demo.kafka.util.DateFormatUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午12:19
 */
public class Producer {

    private final static Logger logger = LogManager.getLogger(Producer.class);

    /**
     * 生产数据到指定Topic的指定分区
     *
     * @param topic
     * @param partition
     */
    public void producerData(String topic, int partition) {
        new Thread(() -> {
            KafkaProducer<String, String> producer = new KafkaProducer<>(ConfigFactory.getProducerConfig());
            while (true) {
                try {
                    String value = getValue();
                    logger.info(String.format("threadId: %s, topic: %s, partition: %s, value: %s", Thread.currentThread().getId(), topic, partition, value));
                    Future<RecordMetadata> sendResult = producer.send(new ProducerRecord<>(topic, partition, "213", value));
//                    RecordMetadata recordMetadata = sendResult.get();
//                    TimeUnit.MILLISECONDS.sleep(1003);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();
    }

    public void producerData_number(String topicA, String topicB) {
        new Thread(() -> {
            AtomicInteger atomicInteger = new AtomicInteger(0);

            KafkaProducer<String, String> producerA = new KafkaProducer<>(ConfigFactory.getProducerConfig());
            KafkaProducer<String, String> producerB = new KafkaProducer<>(ConfigFactory.getProducerConfig());
            while (true) {
                try {
                    String value = String.valueOf(atomicInteger.getAndIncrement());
                    Future<RecordMetadata> sendResult = producerA.send(new ProducerRecord<>(topicA, "1", value));
                    RecordMetadata recordMetadata = sendResult.get();
                    logger.info(String.format("threadId: %s, topic: %s, partition: %s, offset: %s, value: %s", Thread.currentThread().getId(), recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), value));
                    TimeUnit.MILLISECONDS.sleep(1000);

                    value = String.valueOf(atomicInteger.getAndIncrement());
                    Future<RecordMetadata> sendResult1 = producerB.send(new ProducerRecord<>(topicB, "1", value));
                    RecordMetadata recordMetadata1 = sendResult1.get();
                    logger.info(String.format("threadId: %s, topic: %s, partition: %s, offset: %s, value: %s", Thread.currentThread().getId(), recordMetadata1.topic(), recordMetadata1.partition(), recordMetadata1.offset(), value));
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();
    }

    public void producerData_Text(String topic, int partition) {
        new Thread(() -> {
            KafkaProducer<String, String> producer = new KafkaProducer<>(ConfigFactory.getProducerConfig());
            while (true) {
                try {
                    String value = "hotel search";
                    Future<RecordMetadata> sendResult = producer.send(new ProducerRecord<>(topic, partition, "", value));
                    RecordMetadata recordMetadata = sendResult.get();
                    TimeUnit.MILLISECONDS.sleep(5000);
                    logger.info(String.format("threadId: %s, topic: %s, partition: %s, offset: %s, value: %s", Thread.currentThread().getId(), recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), value));
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();
    }

    public static void main(String[] args) {

        Producer producer = new Producer();
        String topicName = "test_producer_consumer";
        producer.producerData(topicName, 0);
        producer.producerData(topicName, 0);
        producer.producerData(topicName, 0);
        producer.producerData(topicName, 1);
        producer.producerData(topicName, 1);
        producer.producerData(topicName, 1);
        producer.producerData(topicName, 2);
        producer.producerData(topicName, 2);
    }


    private String getValue() {
        return Thread.currentThread().getId() + DateFormatUtil.getDateStr(new Date());
    }
}
