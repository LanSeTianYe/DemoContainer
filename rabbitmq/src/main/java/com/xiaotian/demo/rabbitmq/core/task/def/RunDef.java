package com.xiaotian.demo.rabbitmq.core.task.def;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.task.fanout.SendToFanOutTask;
import com.xiaotian.demo.rabbitmq.core.thread.RabbitThreadFactory;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;

public class RunDef {

    public static void main(String[] args) {
        Connection connection = RabbitMqUtil.connect();
        Channel channel = RabbitMqUtil.channel(connection);
        initExchange(channel);
        inintQueue(channel);
        inintBind(channel);


        RabbitThreadFactory rabbitThreadFactory = new RabbitThreadFactory("");

        rabbitThreadFactory.newThread(new SendToDefTask(channel, Const.DEFAULT_QUEUE)).start();
        rabbitThreadFactory.newThread(new SendToFanOutTask(channel, Const.DEFAULT_QUEUE)).start();

        rabbitThreadFactory.newThread(new ReadFromDefTask(channel, Const.DEFAULT_QUEUE)).start();
    }

    private static void initExchange(Channel channel) {
        RabbitMqUtil.createExchange(channel, Const.FANOUT_EXCHANG, BuiltinExchangeType.FANOUT);
    }

    private static void inintQueue(Channel channel) {
        RabbitMqUtil.createQueue(channel, Const.DEFAULT_QUEUE);
    }

    private static void inintBind(Channel channel) {
        RabbitMqUtil.createBind(channel, Const.FANOUT_EXCHANG, Const.DEFAULT_QUEUE, Const.DEFAULT_QUEUE);
    }
}
