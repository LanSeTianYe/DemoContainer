package com.sun.xiaotian.demo.kafka.stream.store;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoNothingProcess implements Processor<String, String> {

    private final static Logger logger = LoggerFactory.getLogger(DoNothingProcess.class);

    private ProcessorContext context;
    private MyCustomStore<String, String> stateStore;

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
        stateStore = (MyCustomStore<String, String>) this.context.getStateStore("test-custom-store");
    }

    @Override
    public void process(String key, String value) {
        stateStore.write(key, value);
        context.forward(key, value);
    }

    @Override
    public void punctuate(long timestamp) {
        logger.error("punctuate: " + timestamp);
    }

    @Override
    public void close() {
        logger.info("DoNothingProcess Close...");
    }
}
