package com.sun.xiaotian.demo.kafka.stream.store;

import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.internals.StateStoreProvider;

import java.util.List;
import java.util.Optional;

public class MyCustomStoreTypeWrapper<K, V> implements MyReadableCustomStore<K, V> {

    private final QueryableStoreType<MyReadableCustomStore<K, V>> customStoreType;
    private final String storeName;
    private final StateStoreProvider provider;

    public MyCustomStoreTypeWrapper(final StateStoreProvider provider,
                                    final String storeName,
                                    final QueryableStoreType<MyReadableCustomStore<K, V>> customStoreType) {

        this.customStoreType = customStoreType;
        this.storeName = storeName;
        this.provider = provider;
    }

    @Override
    public V read(final K key) {
        final List<MyReadableCustomStore<K, V>> stores = provider.stores(storeName, customStoreType);
        Optional<MyReadableCustomStore<K, V>> value = stores.stream().filter(store -> store.read(key) != null).findFirst();
        if (value.isPresent()) {
            return value.get().read(key);
        }
        return null;
    }
}