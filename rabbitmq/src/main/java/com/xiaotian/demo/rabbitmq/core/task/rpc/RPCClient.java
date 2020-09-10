package com.xiaotian.demo.rabbitmq.core.task.rpc;

import com.rabbitmq.client.*;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.util.LogUtil;
import com.xiaotian.demo.rabbitmq.core.util.RabbitMqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RPCClient implements Runnable {

    private static Logger log = LoggerFactory.getLogger(RPCClient.class);

    private final String clientName;
    private final Channel channel;

    public RPCClient(String clientName, Channel channel) {
        this.clientName = clientName;
        this.channel = channel;
    }


    @Override
    public void run() {
        try {
            RPCConsumer rpcConsumer = new RPCConsumer();
            String queueName = RabbitMqUtil.createTempQueue(channel);
            rpcConsumer.setQueueName(queueName);
            log.info("RPCClient create temp queue success, client:{}, queue:{} ", clientName, queueName);

            String requestData = clientName + "ping";
            log.info("RPCClient rsend equest, client:{}, queue:{}, requestData:{} ", clientName, queueName, requestData);
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties().builder();
            AMQP.BasicProperties properties = builder.replyTo(queueName).build();
            channel.basicPublish(Const.RPC_SERVER_EXCHANGE, queueName, properties, requestData.getBytes());

            channel.basicConsume(queueName, false, rpcConsumer);
        } catch (Exception e) {
            LogUtil.logException(log, e);
        }
    }

    private class RPCConsumer implements Consumer {

        public void setQueueName(String queueName) {
            this.queueName = queueName;
        }

        private String queueName;


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
                String data = new String(body);
                log.info("RPCClient handel response, clientName:{}, respnose:{}", clientName, data);
                channel.basicAck(envelope.getDeliveryTag(), false);
                //log.info("RPCClient delete queue, clientName:{}, queueName:{}", clientName, queueName);
                //RabbitMqUtil.deleteQueue(channel, queueName);
                channel.close();
            } catch (Exception e) {
                LogUtil.logException(log, e);
            }

        }
    }

}
