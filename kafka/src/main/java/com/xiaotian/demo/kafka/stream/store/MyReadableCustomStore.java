package com.xiaotian.demo.kafka.stream.store;

public interface MyReadableCustomStore<K,V> {

    V read(K key);

}
