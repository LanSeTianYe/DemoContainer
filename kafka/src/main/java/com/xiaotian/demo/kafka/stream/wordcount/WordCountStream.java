package com.xiaotian.demo.kafka.stream.wordcount;

import com.xiaotian.demo.kafka.AdminClientDemo;
import com.xiaotian.demo.kafka.factory.ConfigFactory;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.Stores;

import java.util.Properties;

public class WordCountStream {

    static final String input = "word-count-input";
    static final String output = "word-count-output";

    public static void main(String[] args) {
        init();
        Properties streamProperties = ConfigFactory.getStreamProperties("wordcount");
        Topology topology = new Topology();
        topology.addSource("source", input);
        topology.addProcessor("split-word-process", () ->
                new Processor() {

                    private ProcessorContext context;
                    private KeyValueStore<String, Integer> keyValueStore;

                    @Override
                    public void init(ProcessorContext context) {
                        System.out.println("start word-count-process");
                        this.context = context;
                        this.keyValueStore = ((KeyValueStore) this.context.getStateStore("words-counts"));
                        this.context.schedule(1000, PunctuationType.STREAM_TIME, timestamp -> {
                            KeyValueIterator<String, Integer> keyValueIterator = this.keyValueStore.all();
                            while (keyValueIterator.hasNext()) {
                                KeyValue<String, Integer> next = keyValueIterator.next();
                                System.out.println(String.format("forward, key: %s, value: %s", next.key, next.value));
                                this.context.forward(next.key, String.valueOf(next.value));
                            }
                            keyValueIterator.close();
                            this.context.commit();
                        });
                    }

                    @Override
                    public void process(Object key, Object value) {
                        String line = String.valueOf(value);
                        if (line == null || line.length() == 0) {
                            return;
                        }
                        System.out.println(String.format("process:%s", value));
                        String[] words = line.toLowerCase().split(" ");
                        for (String word : words) {
                            Integer count = this.keyValueStore.get(word);
                            this.keyValueStore.put(word, count == null ? 1 : count + 1);
                        }
                    }

                    @Override
                    public void punctuate(long timestamp) {
                        //...
                    }

                    @Override
                    public void close() {
                        this.keyValueStore.close();
                    }
                }, "source");
        topology.addStateStore(Stores.keyValueStoreBuilder(Stores.inMemoryKeyValueStore("words-counts"), Serdes.String(), Serdes.Integer()), "split-word-process");
        topology.addSink("sink", output, "split-word-process");
        System.out.println(topology.describe());
        KafkaStreams kafkaStreams = new KafkaStreams(topology, streamProperties);
        kafkaStreams.start();
    }


    private static void init() {
        AdminClientDemo adminClient = new AdminClientDemo();
        if (!adminClient.existsTopic(input)) {
            adminClient.createTopic(input, 2, ((short) 3));
        }
        if (!adminClient.existsTopic(output)) {
            adminClient.createTopic(output, 2, ((short) 3));
        }
    }
}
