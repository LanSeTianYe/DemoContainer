package com.sun.xiaotian.demo.kafka.stream.store;

import org.apache.kafka.streams.processor.StateStore;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.internals.StateStoreProvider;

import java.util.List;

public class MyCustomStoreType<K,V> implements QueryableStoreType<MyReadableCustomStore<K,V>> {

    @Override
    public boolean accepts(StateStore stateStore) {
        return stateStore instanceof MyCustomStore;
    }

    @Override
    public MyReadableCustomStore<K, V> create(StateStoreProvider storeProvider, String storeName) {
        return new MyCustomStoreTypeWrapper<>(storeProvider, storeName, this);
    }

}
