package com.xiaotian.demo.rabbitmq.core.task.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.thread.RabbitThreadFactory;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;


/**
 * 声明 topic 类型交换器 topic_exchange
 * 8个生产者发送消息。
 * fruit_queue       队列 绑定键 fruit.*.*     接收 fruit. 开头的消息
 * fruit_red_queue   队列 绑定键 fruit.*.red   接收 fruit. 开头 .red 结尾 的消息
 * fruit_apple_queue 队列 绑定键 fruit.apple.* 接收 fruit.apple. 开头的消息
 * red_queue         队列 绑定键 *.*.red       接收 fruit. 开头的消息
 * topic_all_queue   队列 绑定键 #             接收 所有的消息
 */
public class RunTopic {

    public static void main(String[] args) {
        Connection connection = RabbitMqUtil.connect();
        Channel channel = RabbitMqUtil.channel(connection);
        initExchange(channel);
        inintQueue(channel);
        inintBind(channel);

        RabbitThreadFactory rabbitThreadFactory = new RabbitThreadFactory("");

        //发送消息
        rabbitThreadFactory.newThread(new SendToTopicTask(channel, Const.FRUIT_APPLE_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToTopicTask(channel, Const.FRUIT_APPLE_GREEN_KEY)).start();
        rabbitThreadFactory.newThread(new SendToTopicTask(channel, Const.FRUIT_BANANA_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToTopicTask(channel, Const.FRUIT_BANANA_YELLOW_KEY)).start();
        rabbitThreadFactory.newThread(new SendToTopicTask(channel, Const.FOOD_APPLE_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToTopicTask(channel, Const.FOOD_APPLE_GREEN_KEY)).start();
        rabbitThreadFactory.newThread(new SendToTopicTask(channel, Const.FOOD_BANANA_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToTopicTask(channel, Const.FOOD_BANANA_YELLOW_KEY)).start();

        rabbitThreadFactory.newThread(new ReadFromTopicTask(channel, Const.FRUIT_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromTopicTask(channel, Const.FRUIT_RED_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromTopicTask(channel, Const.FRUIT_APPLE_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromTopicTask(channel, Const.RED_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromTopicTask(channel, Const.TOPIC_ALL_QUEUE)).start();
    }

    private static void initExchange(Channel channel) {
        RabbitMqUtil.createExchange(channel, Const.TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
    }

    private static void inintQueue(Channel channel) {
        RabbitMqUtil.createQueue(channel, Const.FRUIT_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.FRUIT_RED_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.FRUIT_APPLE_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.RED_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.TOPIC_ALL_QUEUE);
    }

    private static void inintBind(Channel channel) {
        //绑定路由键 fruit.*.* 交换器的fruit.开头的消息发送到队列
        RabbitMqUtil.createBind(channel, Const.TOPIC_EXCHANGE, Const.FRUIT_QUEUE, Const.FRUIT_BIND);
        //绑定路由键 fruit.*.red 交换器的fruit.开头.red结尾的消息发送到队列
        RabbitMqUtil.createBind(channel, Const.TOPIC_EXCHANGE, Const.FRUIT_RED_QUEUE, Const.FRUIT_RED_BIND);
        //绑定路由键 fruit.apple.* 交换器的fruit.apple.开头的消息发送到队列
        RabbitMqUtil.createBind(channel, Const.TOPIC_EXCHANGE, Const.FRUIT_APPLE_QUEUE, Const.FRUIT_APPLE_BIND);
        //绑定路由键 *.*.red 交换器的.red结尾的消息发送到队列
        RabbitMqUtil.createBind(channel, Const.TOPIC_EXCHANGE, Const.RED_QUEUE, Const.RED_BIND);
        //绑定路由键 # 交换器的所有消息发送到队列
        RabbitMqUtil.createBind(channel, Const.TOPIC_EXCHANGE, Const.TOPIC_ALL_QUEUE, Const.TOPIC_ALL_BIND);
    }
}
