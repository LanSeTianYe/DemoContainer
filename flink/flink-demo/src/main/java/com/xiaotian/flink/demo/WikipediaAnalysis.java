package com.xiaotian.flink.demo;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditEvent;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditsSource;

import java.util.concurrent.TimeUnit;

public class WikipediaAnalysis {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment.addSource(new WikipediaEditsSource())
                .keyBy(WikipediaEditEvent::getUser)
                .timeWindow(Time.of(50, TimeUnit.MILLISECONDS))
                .aggregate(new AggregateFunction<WikipediaEditEvent, Tuple2<String, Long>, Tuple2<String, Long>>() {

                    private static final long serialVersionUID = -184305568733432800L;

                    @Override
                    public Tuple2<String, Long> createAccumulator() {
                        return new Tuple2<>("", 100000L);
                    }

                    @Override
                    public Tuple2<String, Long> add(WikipediaEditEvent value, Tuple2<String, Long> accumulator) {
                        accumulator.f0 = value.getUser();
                        accumulator.f1 = accumulator.f1 + value.getByteDiff();
                        return accumulator;
                    }

                    @Override
                    public Tuple2<String, Long> getResult(Tuple2<String, Long> accumulator) {
                        return accumulator;
                    }

                    @Override
                    public Tuple2<String, Long> merge(Tuple2<String, Long> a, Tuple2<String, Long> b) {
                        return new Tuple2<>(a.f0, a.f1 + b.f1);
                    }
                })
                .map(Tuple2::toString)
                .addSink(new FlinkKafkaProducer011<>("192.168.0.201:9092,192.168.0.201:9093,192.168.0.201:9094", "wiki-result", new SimpleStringSchema()));
        executionEnvironment.execute();
    }
}
