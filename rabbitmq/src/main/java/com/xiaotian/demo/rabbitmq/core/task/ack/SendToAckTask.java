package com.xiaotian.demo.rabbitmq.core.task.ack;

import com.rabbitmq.client.Channel;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SendToAckTask implements Runnable {

    private static Logger log = LoggerFactory.getLogger(SendToAckTask.class);

    private final Channel channel;
    private final String routingKey;

    public SendToAckTask(Channel channel, String routingKey) {
        this.channel = channel;
        this.routingKey = routingKey;
    }

    @Override
    public void run() {
        try {
            while (true) {
                log.info("SendToAckTask publish message. routingKey:{}, count:{}", routingKey, "1");
                channel.basicPublish(Const.ACK_FANOUT_EXCHANG, routingKey, null, "1".getBytes());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (Exception e) {
            LogUtil.logException(log, e);
        }
    }
}
