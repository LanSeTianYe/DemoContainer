package com.xiaotian.demo.kafka.stream.low;

import com.xiaotian.demo.kafka.stream.low.listerner.ConsoleGlobalRestoreListener;
import com.xiaotian.demo.kafka.stream.util.DataQuery;
import com.xiaotian.demo.kafka.stream.util.KeyValueStoreDataQuery;
import com.xiaotian.demo.kafka.stream.util.TextDataReader;
import com.xiaotian.demo.kafka.stream.util.TextDataWriter;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LowWordCount {

    private final static Logger logger = LoggerFactory.getLogger(LowWordCount.class);

    private final static String APPLICATION_ID_CONFIG = "low-wordcount";

    private final static String SOURCE_NAME = "source";
    private final static String SOURCE_TOPIC_NAME = "low-source-topic";

    private final static String SINK1_NAME = "SINK1";
    private final static String SINK_TOPIC_NAME = "low-sink-topic";

    private final static String COUNT_STORE_NAME = "Counts";

    private final static String PROCESSOR_1 = LowerProcessor.class.getSimpleName();


    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID_CONFIG);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.201:9092,192.168.0.202:9092,192.168.0.203:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        Map<String, String> topicConfig = new HashMap<>();
        topicConfig.put(TopicConfig.SEGMENT_MS_CONFIG, "10000");

        KeyValueBytesStoreSupplier countStoreSupplier = Stores.inMemoryKeyValueStore(COUNT_STORE_NAME);
        StoreBuilder<KeyValueStore<String, Long>> storeStoreBuilder = Stores
                .keyValueStoreBuilder(countStoreSupplier, Serdes.String(), Serdes.Long())
                .withCachingEnabled()
                .withLoggingDisabled()
                .withLoggingEnabled(topicConfig);

        Topology topology = new Topology();

        //源->处理程序->槽
        topology.addSource(SOURCE_NAME, SOURCE_TOPIC_NAME)
                .addProcessor(PROCESSOR_1, () -> new LowerProcessor(), SOURCE_NAME)
                .addStateStore(storeStoreBuilder, PROCESSOR_1)
                .addSink(SINK1_NAME, SINK_TOPIC_NAME, Serdes.String().serializer(), Serdes.Long().serializer(), PROCESSOR_1);

        logger.info(topology.describe().toString());

        //state query
        DataQuery<String, Long> dataQuery = new KeyValueStoreDataQuery<>();

        KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
        //注册全局数据恢复监听器
        kafkaStreams.setGlobalStateRestoreListener(new ConsoleGlobalRestoreListener());
        //注册KafkaStream状态监听器
        kafkaStreams.setStateListener((newState, oldState) -> {
            logger.info("stateChange: " + oldState.name() + " -> " + newState.name());
            //保证查询接口只调用一次
            if (newState == KafkaStreams.State.RUNNING && oldState == KafkaStreams.State.REBALANCING) {
                dataQuery.query(kafkaStreams, countStoreSupplier.get().name());
            }
        });
        kafkaStreams.start();

        //制造数据
        new TextDataWriter().producer(SOURCE_TOPIC_NAME, 100 * 1000);

        //显示流处理结果
        new TextDataReader().<String, Long>consume(SINK_TOPIC_NAME, (record -> {
            System.out.println("topic: " + record.topic() + "\tkey: \t" + record.key() + "\tvalue:\t" + record.value());
        }), Serdes.String().deserializer().getClass().getName(), Serdes.Long().deserializer().getClass().getName());

        TimeUnit.SECONDS.sleep(100);
        kafkaStreams.close();
        System.exit(0);
    }
}
