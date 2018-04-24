package com.sun.xiaotian.demo.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class LongEventMain {

    private static void handleEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("sequence:" + sequence + " " + event);
    }

    private static void clearEventHandler(LongEvent event, long sequence, boolean endOfBatch) throws InterruptedException {
        event.setValue(0);
        System.out.println("clear: " + event);
    }

    private static void translateOne(LongEvent event, long sequence, ByteBuffer buffer) {
        event.setValue(buffer.getLong(0));
    }

    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 1024;
        LongEventFactory logEventFactory = new LongEventFactory();
        LongEventThreadFactory longEventThreadFactory = new LongEventThreadFactory();

        Disruptor<LongEvent> disruptor = new Disruptor<>(logEventFactory, bufferSize, longEventThreadFactory, ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.handleEventsWith(LongEventMain::handleEvent).then(LongEventMain::clearEventHandler);
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer tempByteBuffer = ByteBuffer.allocate(8);
        for (long l = 0; l < 10000; l++) {
            tempByteBuffer.putLong(0, l);
            ringBuffer.publishEvent(LongEventMain::translateOne, tempByteBuffer);
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
