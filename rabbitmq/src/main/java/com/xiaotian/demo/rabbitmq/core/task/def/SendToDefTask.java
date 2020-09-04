package com.xiaotian.demo.rabbitmq.core.task.def;

import com.rabbitmq.client.Channel;
import com.xiaotian.demo.rabbitmq.core.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public class SendToDefTask implements Runnable {

    private static Logger log = LoggerFactory.getLogger(SendToDefTask.class);
    private static final SecureRandom random = new SecureRandom();

    private final Channel channel;
    private final String routingKey;

    public SendToDefTask(Channel channel, String routingKey) {
        this.channel = channel;
        this.routingKey = routingKey;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String count = String.valueOf(random.nextInt(20));
                log.info("SendToDefTask publish message. routingKey:{}, count:{}", routingKey, count);
                channel.basicPublish("", routingKey, null, count.getBytes());
                TimeUnit.MILLISECONDS.sleep(20);
            }
        } catch (Exception e) {
            LogUtil.logException(log, e);
        }
    }
}
