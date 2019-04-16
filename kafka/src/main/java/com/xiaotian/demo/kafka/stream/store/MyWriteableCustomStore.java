package com.sun.xiaotian.demo.kafka.stream.store;

public interface MyWriteableCustomStore<K,V> extends MyReadableCustomStore<K,V> {

    void write(K key, V value);

}
