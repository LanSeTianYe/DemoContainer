package com.xiaotian.flink.demo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.Objects;

public class SocketWindowWordCount {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> textStream = executionEnvironment.socketTextStream("192.168.0.201", 6666);
        textStream
                .flatMap(new FlatMapFunction<String, WordWithCount>() {
                    @Override
                    public void flatMap(String text, Collector<WordWithCount> collector) throws Exception {
                        if (Objects.nonNull(text)) {
                            Arrays.stream(text.split("\\s")).forEach(w -> collector.collect(new WordWithCount(w, 1L)));
                        }
                    }
                })
                .keyBy("word")
                .timeWindow(Time.seconds(5), Time.seconds(1))
                .reduce((w1, w2) -> new WordWithCount(w1.word, w1.count + w2.count))
                .print()
                .setParallelism(1);
        executionEnvironment.execute();
    }

    public static class WordWithCount {

        public String word;
        public long count;

        public WordWithCount() {
        }

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return word + " : " + count;
        }
    }

}
