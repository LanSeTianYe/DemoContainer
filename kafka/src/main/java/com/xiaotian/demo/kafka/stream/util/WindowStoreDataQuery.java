package com.xiaotian.demo.kafka.stream.util;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.apache.kafka.streams.state.WindowStoreIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class WindowStoreDataQuery<K, V> implements DataQuery {

    private final static Logger logger = LoggerFactory.getLogger(WindowStoreDataQuery.class);

    @Override
    public void query(KafkaStreams streams, String storeName) {
        Thread thread = new Thread(() -> {
            ReadOnlyWindowStore<K, V> windowStore = streams.store(storeName, QueryableStoreTypes.windowStore());
            while (true) {
                K key = (K) "the";
                WindowStoreIterator<V> vWindowStoreIterator = windowStore.fetch(key, 0, System.currentTimeMillis());
                while (vWindowStoreIterator.hasNext()) {
                    System.out.println("storeName: " + storeName + "\tkey: " + key + "\tvalue: " + vWindowStoreIterator.next());
                }
                try {
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
