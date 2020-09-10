package com.xiaotian.demo.rabbitmq.core.task.rpc;

import com.rabbitmq.client.*;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RPCServer implements Runnable {

    private static Logger log = LoggerFactory.getLogger(RPCServer.class);

    private final String serverName;
    private final Channel channel;

    public RPCServer(String serverName, Channel channel) {
        this.serverName = serverName;
        this.channel = channel;
    }


    @Override
    public void run() {
        try {
            RPCConsumer rpcConsumer = new RPCConsumer();
            channel.basicConsume(Const.RPC_SERVER_QUEUE, false, rpcConsumer);
        } catch (Exception e) {
            LogUtil.logException(log, e);
        }
    }

    private class RPCConsumer implements Consumer {

        @Override
        public void handleConsumeOk(String consumerTag) {

        }

        @Override
        public void handleCancelOk(String consumerTag) {

        }

        @Override
        public void handleCancel(String consumerTag) throws IOException {

        }

        @Override
        public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

        }

        @Override
        public void handleRecoverOk(String consumerTag) {

        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
            try {
                String requestData = new String(body);
                log.info("RPCConsumer handleDelivery, serverName:{}, exchange:{}, requestData:{}", serverName, Const.RPC_SERVER_QUEUE, requestData);

                String responseData = requestData + " pong";
                String replyTo = properties.getReplyTo();
                log.info("RPCConsumer response, serverName:{}, exchange:{}, replyTo:{}, responseData:{}", serverName, Const.RPC_SERVER_QUEUE, replyTo, responseData);
                channel.basicPublish("", replyTo, null, responseData.getBytes());
                log.info("RPCConsumer ack, serverName:{}, queue:{}, deliveryTag:{}, ", serverName, Const.RPC_SERVER_QUEUE, envelope.getDeliveryTag());
                channel.basicAck(envelope.getDeliveryTag(), false);
            } catch (IOException e) {
                LogUtil.logException(log, e);
            }
        }
    }
}
