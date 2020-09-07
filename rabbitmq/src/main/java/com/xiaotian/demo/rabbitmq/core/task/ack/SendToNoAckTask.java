package com.xiaotian.demo.rabbitmq.core.task.ack;

import com.rabbitmq.client.Channel;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SendToNoAckTask implements Runnable {

    private static Logger log = LoggerFactory.getLogger(SendToNoAckTask.class);

    private final Channel channel;
    private final String routingKey;

    public SendToNoAckTask(Channel channel, String routingKey) {
        this.channel = channel;
        this.routingKey = routingKey;
    }

    @Override
    public void run() {
        try {
            while (true) {
                log.info("SendToNoAckTask publish message. routingKey:{}, count:{}", routingKey, "2");
                channel.basicPublish(Const.ACK_FANOUT_EXCHANG, routingKey, null, "2".getBytes());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        } catch (Exception e) {
            LogUtil.logException(log, e);
        }
    }
}
