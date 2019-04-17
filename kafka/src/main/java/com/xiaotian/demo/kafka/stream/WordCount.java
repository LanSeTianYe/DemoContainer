package com.xiaotian.demo.kafka.stream;

import com.xiaotian.demo.kafka.messagequeue.Consumer;
import com.xiaotian.demo.kafka.messagequeue.Producer;
import com.xiaotian.demo.kafka.AdminClientDemo;
import com.xiaotian.demo.kafka.factory.ConfigFactory;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午20:02
 */
public class WordCount {

    private final static Logger logger = LogManager.getLogger(WordCount.class);

    public static void main(final String[] args) throws Exception {
        String from = "word-count-topic-from";
        String to = "word-count-topic-to";
        String changeLog = "wordcount-application-counts-store-changelog";
        String repartition = "wordcount-application-counts-store-repartition";

        AdminClientDemo adminClientDemo = new AdminClientDemo();
        adminClientDemo.deleteTopic(from);
        adminClientDemo.deleteTopic(changeLog);
        adminClientDemo.deleteTopic(repartition);
        adminClientDemo.deleteTopic(to);

        TimeUnit.SECONDS.sleep(5);

        adminClientDemo.createTopic(from, 3, Short.valueOf("3"));
        adminClientDemo.createTopic(to, 3, Short.valueOf("3"));
        adminClientDemo.createTopic(changeLog, 3, Short.valueOf("3"));
        adminClientDemo.createTopic(repartition, 3, Short.valueOf("3"));

        Producer producer = new Producer();
        producer.producerData_Text(from, 0);
//        producer.producerData_Text(from, 1);
//        producer.producerData_Text(from, 2);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> textLines = builder.stream(from);
        textLines
                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
                .groupBy((key, word) -> word)
                .count(Materialized.as("counts-store"))
                .toStream()
                .to(to, Produced.with(Serdes.String(), Serdes.Long()));
//        KStream<String, String> textLines = builder.stream(from);
//        KStream<String, String> flatMapValues = textLines.flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")));
//        KGroupedStream<String, String> groupedStream = flatMapValues.groupBy((key, word) -> word);
//        KTable<String, Long> wordCounts = groupedStream.count(Materialized.as("counts-store"));
//        KStream<String, Long> stringLongKStream = wordCounts.toStream();
//        stringLongKStream.to(to, Produced.with(Serdes.String(), Serdes.Long()));
        Topology topology = builder.build();
        logger.info(topology.describe());

        KafkaStreams streams = new KafkaStreams(topology, ConfigFactory.getStreamProperties("wordcount-application"));
        streams.start();

        Consumer consumer = new Consumer();
        consumer.consumer("word_count_group", to, StringDeserializer.class, LongDeserializer.class);
        consumer.consumer("word_count_group", changeLog, StringDeserializer.class, LongDeserializer.class);
        consumer.consumer("word_count_group", repartition, StringDeserializer.class, StringDeserializer.class);
    }
}
