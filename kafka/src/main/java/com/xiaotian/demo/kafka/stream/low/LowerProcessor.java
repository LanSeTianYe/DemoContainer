package com.sun.xiaotian.demo.kafka.stream.low;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

/**
 * 转换为小写字母
 */
public class LowerProcessor implements Processor<String, String> {

    private ProcessorContext context;
    private KeyValueStore<String, Long> kvStore;


    @Override
    public void init(ProcessorContext context) {

        this.context = context;

        context.schedule(1000, PunctuationType.WALL_CLOCK_TIME, (timestamp) -> {
            KeyValueIterator<String, Long> kvIterator = this.kvStore.all();
            while (kvIterator.hasNext()) {
                KeyValue<String, Long> next = kvIterator.next();
                //传递给下一个处理器
                context.forward(next.key, next.value);
            }
            kvIterator.close();
            context.commit();
        });

        this.kvStore = (KeyValueStore<String, Long>) this.context.getStateStore("Counts");
    }

    @Override
    public void process(String key, String textLine) {
        String[] words = textLine.split(" ");
        for (String word : words) {
            Long oldValue = this.kvStore.get(word);
            if (oldValue == null) {
                this.kvStore.put(word, 1l);
            } else {
                this.kvStore.put(word, oldValue + 1l);
            }
        }
    }

    @Override
    public void punctuate(long timestamp) {
        KeyValueIterator<String, Long> kvIterator = this.kvStore.all();
        while (kvIterator.hasNext()) {
            KeyValue<String, Long> entry = kvIterator.next();
            //传递给下一个处理器
            context.forward(entry.key, entry.value);
        }
        kvIterator.close();
        context.commit();
    }

    @Override
    public void close() {
        kvStore.close();
    }
}
