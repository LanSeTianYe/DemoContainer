package com.xiaotian.flink.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;

@Slf4j
public class CustomSources {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment
                .addSource(new SourceFunction<CustomEvent>() {

                    private static final long serialVersionUID = 2087986417425348268L;

                    @Override
                    public void run(SourceContext<CustomEvent> ctx) throws Exception {
                        long num = 0L;
                        while (true) {
                            num++;
                            long timestamp = System.currentTimeMillis();
                            CustomEvent customEvent = new CustomEvent();
                            customEvent.setTimestamp(timestamp);
                            customEvent.setKey("key" + num % 100);
                            customEvent.setValue(num);
                            ctx.collectWithTimestamp(customEvent, timestamp);
                        }
                    }

                    @Override
                    public void cancel() {
                        log.info("cancel custom source.");
                    }
                })
                .keyBy(CustomEvent::getKey)
                .timeWindow(Time.milliseconds(5))
                .reduce(new ReduceFunction<CustomEvent>() {

                    private static final long serialVersionUID = 3248874381285628454L;

                    @Override
                    public CustomEvent reduce(CustomEvent customEvent, CustomEvent t1) throws Exception {
                        customEvent.setValue(customEvent.getValue() + t1.getValue());
                        return customEvent;
                    }
                })
                .map(CustomEvent::toString)
                .addSink(new FlinkKafkaProducer011<>("192.168.0.201:9092,192.168.0.201:9093,192.168.0.201:9094", "custom-result", new SimpleStringSchema()));
        executionEnvironment.execute();
    }


    @Data
    private static class CustomEvent {
        private long timestamp;
        private String key;
        private long value;
    }
}
