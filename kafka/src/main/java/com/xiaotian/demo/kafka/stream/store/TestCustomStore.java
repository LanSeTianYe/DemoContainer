package com.xiaotian.demo.kafka.stream.store;

import com.xiaotian.demo.kafka.builder.KafkaConfigBuilder;
import com.xiaotian.demo.kafka.stream.util.DataQuery;
import com.xiaotian.demo.kafka.stream.util.KeyValueStoreDataQuery;
import com.xiaotian.demo.kafka.stream.util.TextDataReader;
import com.xiaotian.demo.kafka.stream.util.TextDataWriter;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * 测试自定义store
 */
public class TestCustomStore {

    private final static Logger logger = LoggerFactory.getLogger(TestCustomStore.class);

    private final static String APPLICATION_ID_CONFIG = "TestCustomStore";

    private final static String STORE_NAME = "test-custom-store";
    private final static String PROCESS_NAME = "test-custom-process";
    private final static String SOURCE_NAME = "test-custom-source";
    private final static String INPUT_TOPIC_NAME = "test-custom-input";
    private final static String SINK_NAME = "test-custom-sink";
    private final static String OUTPUT_TOPIC_NAME = "test-custom-output";


    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID_CONFIG);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfigBuilder.kafkaServer);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsConfig config = new StreamsConfig(properties);

        MyCustomStoreBuilder customStoreBuilder = new MyCustomStoreBuilder(STORE_NAME);

        Topology topology = new Topology();
        topology.addSource(SOURCE_NAME, INPUT_TOPIC_NAME)
                .addProcessor(PROCESS_NAME, () -> new DoNothingProcess(), SOURCE_NAME)
                .addStateStore(customStoreBuilder, PROCESS_NAME)
                .addSink(SINK_NAME, OUTPUT_TOPIC_NAME, PROCESS_NAME);

        System.out.println(topology.describe());

        KafkaStreams streams = new KafkaStreams(topology, config);

        DataQuery<String, String> dataQuery = new KeyValueStoreDataQuery<>();

        streams.setStateListener((newState, oldState) -> {
            logger.info("stateChange: " + oldState.name() + " -> " + newState.name());
        });
        streams.start();

        //制造数据
        new TextDataWriter().producer(INPUT_TOPIC_NAME, 100 * 1000);

        //显示流处理结果
        new TextDataReader().<String, Long>consume(OUTPUT_TOPIC_NAME, (record -> {
            System.out.println("topic: " + record.topic() + "\tkey: \t" + record.key() + "\tvalue:\t" + record.value());
        }), Serdes.String().deserializer().getClass().getName(), Serdes.String().deserializer().getClass().getName());

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                logger.info("sleep Error: " + e.getMessage(), e);
            }
            MyReadableCustomStore<String, String> store = streams.store(customStoreBuilder.name(), new MyCustomStoreType<String, String>());
            System.out.println("store: " + store.read("0"));
        }).start();

        TimeUnit.SECONDS.sleep(100);
    }
}
