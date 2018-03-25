package com.sun.xiaotian.demo.kafka.stream.util;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class KeyValueStoreDataQuery<K, V> implements DataQuery {

    private final static Logger logger = LoggerFactory.getLogger(KeyValueStoreDataQuery.class);

    public void query(KafkaStreams streams, String storeName) {

        Thread thread = new Thread(() -> {
            ReadOnlyKeyValueStore<K, V> keyValueStore =  null;
            while (true) {
                if (keyValueStore == null) {
                    keyValueStore = streams.store(storeName, QueryableStoreTypes.keyValueStore());
                }
                try (
                        KeyValueIterator<K, V> keyValueIterator = keyValueStore.all();
                ) {
                    while (keyValueIterator.hasNext()) {
                        KeyValue<K, V> keyValue = keyValueIterator.next();
                        System.out.println("storeName: " + storeName + "\tkey: " + keyValue.key + "\tvalue: " + keyValue.value);
                    }
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });

        thread.setName(storeName);
        thread.setDaemon(true);
        thread.start();
    }
}
