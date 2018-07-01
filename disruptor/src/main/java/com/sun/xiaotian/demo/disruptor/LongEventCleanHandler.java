package com.sun.xiaotian.demo.disruptor;


import com.lmax.disruptor.EventHandler;

public class LongEventCleanHandler implements EventHandler<LongEvent>{

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setValue(0L);
    }
}
