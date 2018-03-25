package com.sun.xiaotian.demo.kafka.stream.store;

import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.StateRestoreCallback;
import org.apache.kafka.streams.processor.StateStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MyCustomStore<K, V> implements StateStore, MyWriteableCustomStore<K, V> {

    private final static Logger logger = LoggerFactory.getLogger(MyCustomStore.class);

    private ProcessorContext processorContext;

    private Map<K, V> map;

    private final String name;

    public MyCustomStore(String name) {
        this.name = name;
    }

    @Override
    public V read(K key) {
        return map.get(key);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void init(ProcessorContext context, StateStore root) {
        map = new HashMap<>();
        context.register(root, false, new StateRestoreCallback() {
            @Override
            public void restore(byte[] key, byte[] value) {
                logger.info("restore:" + "\tkey: " + key + "\tvalue:" + value);
            }
        });
        this.processorContext = context;
    }

    @Override
    public void flush() {
        logger.info(name() + ": FLUSH");
    }

    @Override
    public void close() {
        map.clear();
        map = new HashMap<>();
    }

    @Override
    public boolean persistent() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void write(K key, V value) {
        map.put(key, value);
    }
}
