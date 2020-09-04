package com.xiaotian.demo.rabbitmq.core.task.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.thread.RabbitThreadFactory;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;

public class RunDirect {

    public static void main(String[] args) {
        Connection connection = RabbitMqUtil.connect();
        Channel channel = RabbitMqUtil.channel(connection);
        initExchange(channel);
        inintQueue(channel);
        inintBind(channel);


        RabbitThreadFactory rabbitThreadFactory = new RabbitThreadFactory("");

        rabbitThreadFactory.newThread(new SendToDirectTask(channel, Const.FRUIT_APPLE_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToDirectTask(channel, Const.FRUIT_APPLE_GREEN_KEY)).start();
        rabbitThreadFactory.newThread(new SendToDirectTask(channel, Const.FRUIT_BANANA_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToDirectTask(channel, Const.FRUIT_BANANA_YELLOW_KEY)).start();
        rabbitThreadFactory.newThread(new SendToDirectTask(channel, Const.FOOD_APPLE_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToDirectTask(channel, Const.FOOD_APPLE_GREEN_KEY)).start();
        rabbitThreadFactory.newThread(new SendToDirectTask(channel, Const.FOOD_BANANA_RED_KEY)).start();
        rabbitThreadFactory.newThread(new SendToDirectTask(channel, Const.FOOD_BANANA_YELLOW_KEY)).start();

        rabbitThreadFactory.newThread(new ReadFromDirectTask(channel, Const.FRUIT_APPLE_RED_KEY_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromDirectTask(channel, Const.FRUIT_APPLE_GREEN_KEY_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromDirectTask(channel, Const.FRUIT_BANANA_RED_KEY_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromDirectTask(channel, Const.FRUIT_BANANA_YELLOW_KEY_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromDirectTask(channel, Const.FOOD_APPLE_RED_KEY_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromDirectTask(channel, Const.FOOD_APPLE_GREEN_KEY_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromDirectTask(channel, Const.FOOD_BANANA_RED_KEY_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromDirectTask(channel, Const.FOOD_BANANA_YELLOW_KEY_QUEUE)).start();

        rabbitThreadFactory.newThread(new ReadFromDirectTask(channel, Const.FOOD_WATERMELON_GREEN_KEY_QUEUE)).start();

    }

    private static void initExchange(Channel channel) {
        RabbitMqUtil.createExchange(channel, Const.DIRECT_EXCHANG, BuiltinExchangeType.DIRECT);
    }

    private static void inintQueue(Channel channel) {

        RabbitMqUtil.createQueue(channel, Const.FRUIT_APPLE_RED_KEY_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.FRUIT_APPLE_GREEN_KEY_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.FRUIT_BANANA_RED_KEY_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.FRUIT_BANANA_YELLOW_KEY_QUEUE);

        RabbitMqUtil.createQueue(channel, Const.FOOD_APPLE_RED_KEY_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.FOOD_APPLE_GREEN_KEY_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.FOOD_BANANA_RED_KEY_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.FOOD_BANANA_YELLOW_KEY_QUEUE);

        RabbitMqUtil.createQueue(channel, Const.FOOD_WATERMELON_GREEN_KEY_QUEUE);
    }

    private static void inintBind(Channel channel) {

        RabbitMqUtil.createBind(channel, Const.DIRECT_EXCHANG, Const.FRUIT_APPLE_RED_KEY_QUEUE, Const.FRUIT_APPLE_RED_KEY);
        RabbitMqUtil.createBind(channel, Const.DIRECT_EXCHANG, Const.FRUIT_APPLE_GREEN_KEY_QUEUE, Const.FRUIT_APPLE_GREEN_KEY);
        RabbitMqUtil.createBind(channel, Const.DIRECT_EXCHANG, Const.FRUIT_BANANA_RED_KEY_QUEUE, Const.FRUIT_BANANA_RED_KEY);
        RabbitMqUtil.createBind(channel, Const.DIRECT_EXCHANG, Const.FRUIT_BANANA_YELLOW_KEY_QUEUE, Const.FRUIT_BANANA_YELLOW_KEY);

        RabbitMqUtil.createBind(channel, Const.DIRECT_EXCHANG, Const.FOOD_APPLE_RED_KEY_QUEUE, Const.FOOD_APPLE_RED_KEY);
        RabbitMqUtil.createBind(channel, Const.DIRECT_EXCHANG, Const.FOOD_APPLE_GREEN_KEY_QUEUE, Const.FOOD_APPLE_GREEN_KEY);
        RabbitMqUtil.createBind(channel, Const.DIRECT_EXCHANG, Const.FOOD_BANANA_RED_KEY_QUEUE, Const.FOOD_BANANA_RED_KEY);
        RabbitMqUtil.createBind(channel, Const.DIRECT_EXCHANG, Const.FOOD_BANANA_YELLOW_KEY_QUEUE, Const.FOOD_BANANA_YELLOW_KEY);

        RabbitMqUtil.createBind(channel, Const.DIRECT_EXCHANG, Const.FOOD_WATERMELON_GREEN_KEY_QUEUE, Const.FOOD_WATERMELON_GREEN_KEY);
    }
}
