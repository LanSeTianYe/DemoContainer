package com.xiaotian.demo.rabbitmq.core.task.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.thread.RabbitThreadFactory;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;

public class RunTopic {

    public static void main(String[] args) {
        Connection connection = RabbitMqUtil.connect();
        Channel channel = RabbitMqUtil.channel(connection);
        initExchange(channel);
        inintQueue(channel);
        inintBind(channel);

        RabbitThreadFactory rabbitThreadFactory = new RabbitThreadFactory("");

    }

    private static void initExchange(Channel channel) {
        RabbitMqUtil.createExchange(channel, Const.TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
    }

    private static void inintQueue(Channel channel) {

        RabbitMqUtil.createQueue(channel, Const.FRUIT_APPLE_RED_KEY_QUEUE);
    }

    private static void inintBind(Channel channel) {

        RabbitMqUtil.createBind(channel, Const.DIRECT_EXCHANG, Const.FRUIT_APPLE_RED_KEY_QUEUE, Const.FRUIT_APPLE_RED_KEY);

    }
}
