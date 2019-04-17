package com.xiaotian.demo.kafka.pipe;

import com.xiaotian.demo.kafka.messagequeue.Producer;
import com.xiaotian.demo.kafka.AdminClientDemo;
import com.xiaotian.demo.kafka.factory.ConfigFactory;
import com.xiaotian.demo.kafka.messagequeue.Consumer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年05月31日 下午17:53
 */

public class PipeDemo {

    private final static Logger logger = LogManager.getLogger(PipeDemo.class);


    public static void main(String[] args) throws InterruptedException {

        String from = "pipe_from";

        String to = "pipe_to";

        AdminClientDemo adminClientDemo = new AdminClientDemo();
        adminClientDemo.deleteTopic(from);
        adminClientDemo.deleteTopic(to);
        TimeUnit.SECONDS.sleep(3);
        adminClientDemo.createTopic(from, 3, Short.valueOf("3"));
        adminClientDemo.createTopic(to, 3, Short.valueOf("3"));

        Producer producer = new Producer();
        producer.producerData(from, 0);
        producer.producerData(from, 1);
        producer.producerData(from, 2);


        List<InetSocketAddress> inetSocketAddresses = new ArrayList<>();
        inetSocketAddresses.add(new InetSocketAddress("192.168.0.201", 9092));
        inetSocketAddresses.add(new InetSocketAddress("192.168.0.202", 9092));
        inetSocketAddresses.add(new InetSocketAddress("192.168.0.203", 9092));
        //pipe
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> source = streamsBuilder.stream(from);
        source.mapValues(value -> {
            return new Date().getTime();
        }).to(to, Produced.valueSerde(Serdes.Long()));
//        source.to(to, Produced.streamPartitioner(new DefaultStreamPartitioner<>(new StringSerializer(), Cluster.bootstrap(inetSocketAddresses), to)));
//        source.to(to);
        Topology topology = streamsBuilder.build();
        logger.info(topology.describe());

        Properties streamProperties = ConfigFactory.getStreamProperties("pipe-stream");
        KafkaStreams kafkaStreams = new KafkaStreams(topology, streamProperties);
        kafkaStreams.start();

        Consumer consumer = new Consumer();
        consumer.consumerFromLatest_Long("pipe_group", to);
    }
}
