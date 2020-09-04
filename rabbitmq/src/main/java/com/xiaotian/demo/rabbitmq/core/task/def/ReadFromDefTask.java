package com.xiaotian.demo.rabbitmq.core.task.def;

import com.rabbitmq.client.*;
import com.xiaotian.demo.rabbitmq.core.util.LogUtil;
import com.xiaotian.demo.rabbitmq.core.util.ThreadIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReadFromDefTask implements Runnable {


    private static Logger log = LoggerFactory.getLogger(ReadFromDefTask.class);
    private static long totalCount = 0;
    private final String tag;

    private final Channel channel;
    private final String queueName;

    public ReadFromDefTask(Channel channel, String queueName) {
        this.channel = channel;
        this.queueName = queueName;
        this.tag = ThreadIDUtil.getTag(this.getClass());
    }


    @Override
    public void run() {
        try {
            ReadFromDefTaskConsumer consumer = new ReadFromDefTaskConsumer();
            channel.basicConsume(queueName, true, tag, consumer);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e) {
            LogUtil.logException(log, e);
        }
    }


    private class ReadFromDefTaskConsumer implements Consumer {

        @Override
        public void handleConsumeOk(String consumerTag) {

        }

        @Override
        public void handleCancelOk(String consumerTag) {

        }

        @Override
        public void handleCancel(String consumerTag) throws IOException {

        }

        @Override
        public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

        }

        @Override
        public void handleRecoverOk(String consumerTag) {

        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
            String newCount = new String(body);
            totalCount += Long.valueOf(newCount);
            log.info("ReadFromDefTask, exchange:{}, queue:{}, routingKey:{}, deliveryTag:{}, consumerTag:{}, newCount:{}, totalCount:{}", envelope.getExchange(), queueName, envelope.getRoutingKey(), envelope.getDeliveryTag(), consumerTag, newCount, totalCount);
        }
    }

}
