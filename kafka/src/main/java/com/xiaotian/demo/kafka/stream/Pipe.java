package com.xiaotian.demo.kafka.stream;

import com.xiaotian.demo.kafka.stream.util.TextDataReader;
import com.xiaotian.demo.kafka.stream.util.TextDataWriter;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Pipe {

    private final static Logger logger = LoggerFactory.getLogger(Pipe.class);

    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.201:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> source = streamsBuilder.stream("streams-plaintext-input");
        source.to("streams-pipe-out");

        Topology topology = streamsBuilder.build();
        System.out.println(topology.describe());

        KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);

        kafkaStreams.start();


        //制造数据
        new TextDataWriter().producer("streams-plaintext-input", 100 * 1000);

        //显示流处理结果
        new TextDataReader().<String, String>consume("streams-pipe-out", (record -> {
            System.out.println("topic: " + record.topic() + "\tkey: \t" + record.key() + "\tvalue:\t" + record.value());
        }), Serdes.String().deserializer().getClass().getName(), Serdes.String().deserializer().getClass().getName());

        TimeUnit.SECONDS.sleep(100);
    }
}
