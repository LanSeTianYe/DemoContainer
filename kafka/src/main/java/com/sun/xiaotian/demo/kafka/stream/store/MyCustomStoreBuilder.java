package com.sun.xiaotian.demo.kafka.stream.store;

import org.apache.kafka.streams.state.StoreBuilder;

import java.util.HashMap;
import java.util.Map;

public class MyCustomStoreBuilder<K,V> implements StoreBuilder<MyCustomStore<K, V>> {

    private final String name;

    private MyCustomStoreSupplier customStoreSupplier;

    public MyCustomStoreBuilder(String name) {
        this.name = name;
    }

    @Override
    public StoreBuilder<MyCustomStore<K, V>> withCachingEnabled() {
        return this;
    }

    @Override
    public StoreBuilder<MyCustomStore<K, V>> withLoggingEnabled(Map<String, String> config) {
        return this;
    }

    @Override
    public StoreBuilder<MyCustomStore<K, V>> withLoggingDisabled() {
        return this;
    }

    @Override
    public MyCustomStore<K, V> build() {
        customStoreSupplier = new MyCustomStoreSupplier(name);
        return ((MyCustomStore) customStoreSupplier.get());
    }

    @Override
    public Map<String, String> logConfig() {
        return new HashMap<>();
    }

    @Override
    public boolean loggingEnabled() {
        return false;
    }

    @Override
    public String name() {
        return name;
    }
}
