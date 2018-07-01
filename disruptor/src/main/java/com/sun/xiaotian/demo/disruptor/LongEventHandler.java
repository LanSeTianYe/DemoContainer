package com.sun.xiaotian.demo.disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(String.format("thread_id: %s, sequence: %s sequence: %s",Thread.currentThread().getId(), sequence, event));
    }

}
