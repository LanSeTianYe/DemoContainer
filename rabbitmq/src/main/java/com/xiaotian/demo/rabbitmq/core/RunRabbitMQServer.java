package com.xiaotian.demo.rabbitmq.core;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RunRabbitMQServer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.connect();
        Channel channel = RabbitMqUtil.channel(connection);
        channel.close();
        connection.close();
    }
}
