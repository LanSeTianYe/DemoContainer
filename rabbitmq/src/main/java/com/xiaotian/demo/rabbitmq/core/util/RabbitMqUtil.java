package com.xiaotian.demo.rabbitmq.core.util;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Rabbit MQ 工具类
 */
public class RabbitMqUtil {

    public static Connection connect() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("10.0.8.36");
            factory.setPort(5672);
            factory.setUsername("admin");
            factory.setPassword("admin");
            return factory.newConnection();
        } catch (Exception e) {
            throw new RuntimeException("建立连接出错", e);
        }
    }

    public static Channel channel(Connection connection) {
        try {
            return connection.createChannel();
        } catch (Exception e) {
            throw new RuntimeException("获取通道连接出错", e);
        }
    }

    public static void createExchange(Channel channel, String exchange, BuiltinExchangeType type) {
        try {
            channel.exchangeDeclare(exchange, type, true, false, null);
        } catch (IOException e) {
            throw new RuntimeException("创建交换器出错", e);
        }
    }

    public static void createQueue(Channel channel, String queueName) {
        try {
            channel.queueDeclare(queueName, true, false, false, null);
        } catch (IOException e) {
            throw new RuntimeException("创建队列出错", e);
        }
    }

    public static String createTempQueue(Channel channel) {
        try {
            AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(null, true, false, false, null);
            return declareOk.getQueue();
        } catch (IOException e) {
            throw new RuntimeException("创建临时队列出错", e);
        }
    }

    public static void createBind(Channel channel, String exchange, String queueName, String routikgKey) {
        try {
            channel.queueBind(queueName, exchange, routikgKey);
        } catch (IOException e) {
            throw new RuntimeException("创建队列出错", e);
        }
    }

}
