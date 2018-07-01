package com.sun.xiaotian.demo.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.TimeUnit;

public class LongEventMain {

    private static final int bufferSize = 1 << 3;

    private static final Disruptor<LongEvent> disruptor = new Disruptor<>(new LongEventFactory(), bufferSize, new LongEventThreadFactory(), ProducerType.MULTI, new BlockingWaitStrategy());

    private static RingBuffer<LongEvent> ringBuffer;

    private static final int producerCount = 8;

    static {
        disruptor.handleEventsWith(new LongEventHandler(), new LongEventHandler(), new LongEventHandler()).then(new LongEventCleanHandler());
        disruptor.start();
        ringBuffer = disruptor.getRingBuffer();
    }

    public static void main(String[] args) throws InterruptedException {
        initProducer();
        TimeUnit.SECONDS.sleep(15);
        System.exit(1);
    }

    private static void initProducer() {
        for (int i = 0; i < producerCount; i++) {
            new Thread(() -> {
                for (long l = 0; l < 10000; l++) {
                    long next = ringBuffer.next();
                    try {
                        LongEvent longEvent = ringBuffer.get(next);
                        longEvent.setValue(l);
                    } finally {
                        ringBuffer.publish(next);
                    }
                }
            }).start();
        }
    }
}
