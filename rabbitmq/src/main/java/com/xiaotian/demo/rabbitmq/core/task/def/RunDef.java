package com.xiaotian.demo.rabbitmq.core.task.def;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.task.fanout.SendToFanOutTask;
import com.xiaotian.demo.rabbitmq.core.thread.RabbitThreadFactory;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;

/**
 * 默认交换器
 * RabbitMQ 存在一个默认的 direct 类型的交换器,所有的队列都绑定到该交换器上,
 * 绑定的路由键是队列的名字。
 * <p>
 * 下面列子中，default_queue 队列绑定到 fanout_exchange 交换器。
 * 两个生产者，分别发送消息到 默认交换器和fanout_exchange交换器，路由键都是 `default_queue`。
 * 一个消费者，从 default_queue 队列消费，会读取到两个生产者发送的消息。
 */
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
