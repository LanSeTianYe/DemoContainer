package com.xiaotian.demo.rabbitmq.core.task.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.thread.RabbitThreadFactory;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;

/**
 * 声明 fanout 类型交换器 fanout_exchange
 * 声明两个队列 all_fanout 和 all_fanout_1,绑定到 fanout_exchange 交换器。
 * 8个生产者发送数据
 * 2个消费者分别从 all_fanout 和 all_fanout_1 队列读取数据。都可以读取到发送到 fanout_exchange 交换器的全部数据。
 */
public class RunFanout {

    public static void main(String[] args) {
        Connection connection = RabbitMqUtil.connect();
        Channel channel = RabbitMqUtil.channel(connection);
        initExchange(channel);
        inintQueue(channel);
        inintBind(channel);

        RabbitThreadFactory rabbitThreadFactory = new RabbitThreadFactory("");

        rabbitThreadFactory.newThread(new SendToFanOutTask(RabbitMqUtil.channel(connection), Const.FRUIT_APPLE_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToFanOutTask(RabbitMqUtil.channel(connection), Const.FRUIT_APPLE_GREEN_KEY)).start();
        rabbitThreadFactory.newThread(new SendToFanOutTask(RabbitMqUtil.channel(connection), Const.FRUIT_BANANA_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToFanOutTask(RabbitMqUtil.channel(connection), Const.FRUIT_BANANA_YELLOW_KEY)).start();
        rabbitThreadFactory.newThread(new SendToFanOutTask(RabbitMqUtil.channel(connection), Const.FOOD_APPLE_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToFanOutTask(RabbitMqUtil.channel(connection), Const.FOOD_APPLE_GREEN_KEY)).start();
        rabbitThreadFactory.newThread(new SendToFanOutTask(RabbitMqUtil.channel(connection), Const.FOOD_BANANA_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToFanOutTask(RabbitMqUtil.channel(connection), Const.FOOD_BANANA_YELLOW_KEY)).start();

        rabbitThreadFactory.newThread(new ReadFromFanOutTask(RabbitMqUtil.channel(connection), Const.ALL_FANOUT)).start();
        rabbitThreadFactory.newThread(new ReadFromFanOutTask(RabbitMqUtil.channel(connection), Const.ALL_FANOUT_1)).start();
    }

    private static void initExchange(Channel channel) {
        RabbitMqUtil.createExchange(channel, Const.FANOUT_EXCHANG, BuiltinExchangeType.FANOUT);
    }

    private static void inintQueue(Channel channel) {
        RabbitMqUtil.createQueue(channel, Const.ALL_FANOUT);
        RabbitMqUtil.createQueue(channel, Const.ALL_FANOUT_1);
    }

    private static void inintBind(Channel channel) {
        RabbitMqUtil.createBind(channel, Const.FANOUT_EXCHANG, Const.ALL_FANOUT, "");
        RabbitMqUtil.createBind(channel, Const.FANOUT_EXCHANG, Const.ALL_FANOUT_1, "");
    }
}
