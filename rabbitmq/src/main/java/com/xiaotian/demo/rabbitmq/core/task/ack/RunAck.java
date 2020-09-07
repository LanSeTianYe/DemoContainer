package com.xiaotian.demo.rabbitmq.core.task.ack;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.thread.RabbitThreadFactory;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送方确认
 * ackChannel.confirmSelect() 开启发送方确认模式
 * deliveryTag 确认消息的id，发送的第一个消息从1开始。
 * multiple 是否确认多个。
 */
public class RunAck {

    private static Logger log = LoggerFactory.getLogger(RunAck.class);

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitMqUtil.connect();

        Channel channel = RabbitMqUtil.channel(connection);
        initExchange(channel);
        inintQueue(channel);
        inintBind(channel);
        channel.close();
        Channel ackChannel = RabbitMqUtil.channel(connection);
        ackChannel.confirmSelect();
        Channel readhannel = RabbitMqUtil.channel(connection);

        RabbitThreadFactory rabbitThreadFactory = new RabbitThreadFactory("");

        rabbitThreadFactory.newThread(new SendToAckTask(ackChannel, Const.ACK_QUEUE)).start();
        rabbitThreadFactory.newThread(new SendToNoAckTask(ackChannel, Const.ACK_QUEUE)).start();

        ackChannel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) {
                log.info("RunAck handleAck message storage success, deliveryTag:{}, multiple:{}", deliveryTag, multiple);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) {
                log.info("RunAck handleNack message is lose, deliveryTag:{}, multiple:{}", deliveryTag, multiple);
            }
        });

        rabbitThreadFactory.newThread(new ReadFromAckTask(readhannel, Const.ACK_QUEUE)).start();
    }

    private static void initExchange(Channel channel) {
        RabbitMqUtil.createExchange(channel, Const.ACK_FANOUT_EXCHANG, BuiltinExchangeType.FANOUT);
    }

    private static void inintQueue(Channel channel) {
        RabbitMqUtil.createQueue(channel, Const.ACK_QUEUE);
    }

    private static void inintBind(Channel channel) {
        RabbitMqUtil.createBind(channel, Const.ACK_FANOUT_EXCHANG, Const.ACK_QUEUE, Const.ACK_QUEUE);
    }
}
