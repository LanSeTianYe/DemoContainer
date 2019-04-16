package com.xiaotian.demo.kafka.stream.store;

import org.apache.kafka.streams.processor.StateStore;
import org.apache.kafka.streams.state.StoreSupplier;

public class MyCustomStoreSupplier implements StoreSupplier {

    private MyCustomStore myCustomStore = null;
    private String name;

    public MyCustomStoreSupplier(String name) {
        this.name = name;
    }

    @Override

    public String name() {
        return MyCustomStoreSupplier.class.getSimpleName();
    }

    @Override
    public StateStore get() {
        if (myCustomStore == null) {
            myCustomStore = new MyCustomStore(name);
        }
        return myCustomStore;
    }

    @Override
    public String metricsScope() {
        return name() + "_scope";
    }
}
