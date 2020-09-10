package com.xiaotian.demo.rabbitmq.core.task.rpc;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.thread.RabbitThreadFactory;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;

import java.util.concurrent.TimeUnit;


/**
 * RPC 客户端和服务器
 * 客户端发送请求到Fanout交换器，并创建临时队列，监听队列，临时队列绑定到 direct 交换器
 * 服务器接收客户端的消息，然后把响应发送到 direct 交换器，路由键是 队列的名字
 */
public class RunRPC {

    public static void main(String[] args) throws InterruptedException {
        Connection connection = RabbitMqUtil.connect();
        Channel channel = RabbitMqUtil.channel(connection);
        initExchange(channel);
        inintQueue(channel);
        inintBind(channel);

        RabbitThreadFactory rabbitThreadFactory = new RabbitThreadFactory("");

        rabbitThreadFactory.newThread(new RPCServer("server_1", RabbitMqUtil.channel(connection))).start();
        rabbitThreadFactory.newThread(new RPCServer("server_2", RabbitMqUtil.channel(connection))).start();
        rabbitThreadFactory.newThread(new RPCServer("server_3", RabbitMqUtil.channel(connection))).start();
        rabbitThreadFactory.newThread(new RPCServer("server_4", RabbitMqUtil.channel(connection))).start();
        rabbitThreadFactory.newThread(new RPCServer("server_5", RabbitMqUtil.channel(connection))).start();

        TimeUnit.SECONDS.sleep(1);
        int clientId = 1;
        while (true) {
            rabbitThreadFactory.newThread(new RPCClient("client_" + clientId, RabbitMqUtil.channel(connection))).start();
            clientId++;
            TimeUnit.MILLISECONDS.sleep(100);
        }

    }

    private static void initExchange(Channel channel) {
        RabbitMqUtil.createExchange(channel, Const.RPC_SERVER_EXCHANGE, BuiltinExchangeType.FANOUT);
        RabbitMqUtil.createExchange(channel, Const.RPC_RESULT_EXCHANGE, BuiltinExchangeType.DIRECT);
    }

    private static void inintQueue(Channel channel) {
        RabbitMqUtil.createQueue(channel, Const.RPC_SERVER_QUEUE);
    }

    private static void inintBind(Channel channel) {
        RabbitMqUtil.createBind(channel, Const.RPC_SERVER_EXCHANGE, Const.RPC_SERVER_QUEUE, "");
    }
}
