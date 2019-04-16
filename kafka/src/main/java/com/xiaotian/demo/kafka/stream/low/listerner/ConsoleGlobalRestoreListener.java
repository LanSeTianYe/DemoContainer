package com.xiaotian.demo.kafka.stream.low.listerner;

import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.streams.processor.StateRestoreListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据恢复过程监控
 */
public class ConsoleGlobalRestoreListener implements StateRestoreListener {

    private final static Logger logger = LoggerFactory.getLogger(ConsoleGlobalRestoreListener.class);


    @Override
    public void onRestoreStart(TopicPartition topicPartition, String storeName, long startingOffset, long endingOffset) {
        logger.info("RestoreStart" + "TopicPartition: " + topicPartition.topic() + "\tTotalRestored:" + (endingOffset - startingOffset + 1));
    }

    @Override
    public void onBatchRestored(TopicPartition topicPartition, String storeName, long batchEndOffset, long numRestored) {
        logger.info("Restored batch " + numRestored + " for " + storeName + " partition " + topicPartition.partition());
    }

    @Override
    public void onRestoreEnd(TopicPartition topicPartition, String storeName, long totalRestored) {
        logger.info("RestoreEnd: " + "TopicPartition: " + topicPartition.topic() + "\tTotalRestored:" + totalRestored);
    }

}
