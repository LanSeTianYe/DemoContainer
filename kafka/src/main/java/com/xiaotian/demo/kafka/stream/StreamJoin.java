package com.xiaotian.demo.kafka.stream;

import com.xiaotian.demo.kafka.messagequeue.Consumer;
import com.xiaotian.demo.kafka.messagequeue.Producer;
import com.xiaotian.demo.kafka.AdminClientDemo;
import com.xiaotian.demo.kafka.factory.ConfigFactory;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年06月01日 下午11:39
 */
public class StreamJoin {

    private final static Logger logger = LogManager.getLogger(StreamJoin.class);


    public static void main(String[] args) throws InterruptedException {
        String a_stream = "join-a";
        String b_stream = "join-b";
        String join_result = "join-result";

        AdminClientDemo adminClientDemo = new AdminClientDemo();
        if (adminClientDemo.existsTopic(a_stream)) {
            adminClientDemo.deleteTopic(a_stream);
        }
        if (adminClientDemo.existsTopic(b_stream)) {
            adminClientDemo.deleteTopic(b_stream);
        }
        if (adminClientDemo.existsTopic(join_result)) {
            adminClientDemo.deleteTopic(join_result);
        }

        TimeUnit.SECONDS.sleep(3);

        adminClientDemo.createTopic(a_stream, 3, Short.valueOf("3"));
        adminClientDemo.createTopic(b_stream, 3, Short.valueOf("3"));
        adminClientDemo.createTopic(join_result, 3, Short.valueOf("3"));

        Producer producer = new Producer();
        producer.producerData_number(a_stream, b_stream);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> streamA = builder.stream(a_stream);
        KStream<String, String> streamB = builder.stream(b_stream);

        KStream<String, Integer> joinStream = streamA.join(streamB, new ValueJoiner<String, String, Integer>() {
            @Override
            public Integer apply(String value1, String value2) {
                return Integer.valueOf(value1) + Integer.valueOf(value2);
            }
        }, JoinWindows.of(1300));

        joinStream.to(join_result, Produced.with(Serdes.String(), Serdes.Integer()));

        Topology topology = builder.build();
        logger.info(topology.describe());

        KafkaStreams streams = new KafkaStreams(topology, ConfigFactory.getStreamProperties("join-application"));
        streams.start();

        Consumer consumer = new Consumer();
        consumer.consumer("join_group", join_result, StringDeserializer.class, IntegerDeserializer.class);
    }
}
