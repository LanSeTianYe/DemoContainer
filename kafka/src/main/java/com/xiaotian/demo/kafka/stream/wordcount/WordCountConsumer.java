package com.xiaotian.demo.kafka.stream.wordcount;

import com.xiaotian.demo.kafka.messagequeue.Consumer;

public class WordCountConsumer {

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
//        consumer.consumerFromBegin("21", WordCountStream.output);
        consumer.consumerFromBegin("211", "wordcount-words-counts-changelog");
    }
}
