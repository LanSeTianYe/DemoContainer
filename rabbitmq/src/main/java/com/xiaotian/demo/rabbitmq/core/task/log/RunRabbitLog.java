package com.xiaotian.demo.rabbitmq.core.task.log;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.thread.RabbitThreadFactory;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;


/**
 * 从 amq.rabbitmq.log 默认日志交换器读取数据
 * <p>
 * rabbit_log_all_queue           队列 绑定读取所有级别的日志
 * rabbit_error_log_debug_queue   队列 绑定error级别的日志
 */
public class RunRabbitLog {

    public static void main(String[] args) {
        Connection connection = RabbitMqUtil.connect();
        Channel channel = RabbitMqUtil.channel(connection);
        initExchange(channel);
        inintQueue(channel);
        inintBind(channel);

        RabbitThreadFactory rabbitThreadFactory = new RabbitThreadFactory("");


        rabbitThreadFactory.newThread(new ReadFromRabbitLogTask(channel, Const.RAIBBIT_LOG_ALL_QUEUE)).start();
        rabbitThreadFactory.newThread(new ReadFromRabbitLogTask(channel, Const.RAIBBIT_LOG_ERROR_QUEUE)).start();
    }

    private static void initExchange(Channel channel) {
        RabbitMqUtil.createExchange(channel, Const.TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);
    }

    private static void inintQueue(Channel channel) {
        RabbitMqUtil.createQueue(channel, Const.RAIBBIT_LOG_ALL_QUEUE);
        RabbitMqUtil.createQueue(channel, Const.RAIBBIT_LOG_ERROR_QUEUE);
    }

    private static void inintBind(Channel channel) {
        RabbitMqUtil.createBind(channel, Const.RABBITMQ_LOG_EXCHANGE, Const.RAIBBIT_LOG_ALL_QUEUE, Const.RAIBBIT_LOG_ALL_BIND);
        RabbitMqUtil.createBind(channel, Const.RABBITMQ_LOG_EXCHANGE, Const.RAIBBIT_LOG_ERROR_QUEUE, Const.RAIBBIT_LOG_ERROR_BIND);
    }
}
