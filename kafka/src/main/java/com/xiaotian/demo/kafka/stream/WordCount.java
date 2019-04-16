package com.sun.xiaotian.demo.kafka.stream;

import com.sun.xiaotian.demo.kafka.stream.util.DataQuery;
import com.sun.xiaotian.demo.kafka.stream.util.KeyValueStoreDataQuery;
import com.sun.xiaotian.demo.kafka.stream.util.TextDataReader;
import com.sun.xiaotian.demo.kafka.stream.util.TextDataWriter;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 单词数量统计
 */
public class WordCount {

    private final static Logger logger = LoggerFactory.getLogger(WordCount.class);

    private final static String INPUT_TOPIC = "streams-plaintext-input";
    private final static String OUTPUT_TOPIC = "streams-wordcount-output";

    private final static String APPLICATION_ID_CONFIG = "streams-wordcount";

    public static void main(String[] args) throws InterruptedException {

        //流配置
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID_CONFIG);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.201:9092,192.168.0.202:9092,192.168.0.203:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        //kTable 缓存大小以及缓存时间，一个条件达到数据将会写入state store 并且发送给下一个节点
        //缓存大小设置为零的时候代表关闭缓存
        properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 10 * 1024 * 1024L);
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 1 * 1000l);

        //日志文件分片大小
        properties.put(StreamsConfig.topicPrefix(TopicConfig.SEGMENT_BYTES_CONFIG), 1024 * 1024);

        //从topic构造流
        StreamsBuilder builder = new StreamsBuilder();
        //可以指定序列化和反序列化器
        KStream<String, String> source = builder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), Serdes.String()));
        //无状态 (a,"a b c")=>(a,a)(a,b)(a,c)
        KStream<String, String> valueSplitStream = source.flatMapValues((value -> Arrays.asList(value.toLowerCase().split("\\W+"))));
        //有状态，会有一个中间的topic
        KGroupedStream<String, String> groupedStream = valueSplitStream.groupBy((key, value) -> value);

        String storeName = "counts-store";
        KTable<String, Long> kTable = groupedStream.count(Materialized.as(storeName));
        //将kTable的结果存入另一个topic
        kTable.toStream().to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.Long()));
        //构造拓补
        Topology topology = builder.build();
        System.out.println(topology.describe());

        DataQuery<String, Long> dataQuery = new KeyValueStoreDataQuery<>();
        //执行流程
        KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
        kafkaStreams.setStateListener((newState, oldState) -> {
            logger.info("stateChange: " + oldState.name() + " -> " + newState.name());
            //保证查询接口只调用一次
            if (newState == KafkaStreams.State.RUNNING && oldState == KafkaStreams.State.REBALANCING) {
                dataQuery.query(kafkaStreams, storeName);
            }
        });
        //设置异常处理
        kafkaStreams.setUncaughtExceptionHandler((thread, throwable) -> {
            logger.error(throwable.getMessage(), throwable);
        });

        //制造数据
        new TextDataWriter().producer(INPUT_TOPIC, 100 * 1000);

        //显示流处理结果
        new TextDataReader().<String, Long>consume(OUTPUT_TOPIC, (record -> {
            System.out.println("topic: " + record.topic() + "\tkey: " + record.key() + "\tvalue: " + record.value());
        }), Serdes.String().deserializer().getClass().getName(), Serdes.Long().deserializer().getClass().getName());

        kafkaStreams.start();

        TimeUnit.SECONDS.sleep(100);
        kafkaStreams.close();
        System.exit(0);
    }
}
