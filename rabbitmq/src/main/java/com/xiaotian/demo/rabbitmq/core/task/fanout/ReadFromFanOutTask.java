package com.xiaotian.demo.rabbitmq.core.task.fanout;

import com.rabbitmq.client.*;
import com.xiaotian.demo.rabbitmq.core.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ReadFromFanOutTask implements Runnable {

    private static Logger log = LoggerFactory.getLogger(ReadFromFanOutTask.class);
    private static long totalCount = 0;

    private final Channel channel;
    private final String queueName;

    public ReadFromFanOutTask(Channel channel, String queueName) {
        this.channel = channel;
        this.queueName = queueName;
    }

    @Override
    public void run() {
        try {
            while (true) {
                channel.basicConsume(queueName, false, new ReadFromFanOutTaskConsumer());
            }
        } catch (Exception e) {
            LogUtil.logException(log, e);
        }
    }


    private class ReadFromFanOutTaskConsumer implements Consumer {

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
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            String newCount = new String(body);
            totalCount += Long.valueOf(newCount);
            log.info("ReadFromFanOutTask, exchange:{}, routingKey:{}, deliveryTag:{}, consumerTag:{}, newCount:{}, totalCount:{}", envelope.getExchange(), envelope.getRoutingKey(), envelope.getDeliveryTag(), consumerTag, newCount, totalCount);
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }

}
