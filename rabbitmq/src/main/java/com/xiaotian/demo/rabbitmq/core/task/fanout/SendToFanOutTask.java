package com.xiaotian.demo.rabbitmq.core.task.fanout;

import com.rabbitmq.client.Channel;
import com.xiaotian.demo.rabbitmq.core.constant.Const;
import com.xiaotian.demo.rabbitmq.core.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public class SendToFanOutTask implements Runnable {

    private static Logger log = LoggerFactory.getLogger(SendToFanOutTask.class);
    private static final SecureRandom random = new SecureRandom();

    private final Channel channel;
    private final String routingKey;

    public SendToFanOutTask(Channel channel, String routingKey) {
        this.channel = channel;
        this.routingKey = routingKey;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String count = String.valueOf(random.nextInt(20));
                log.info("SendToFanOutTask publish message. routingKey:{}, count:{}", routingKey, count);
                channel.basicPublish(Const.FANOUT_EXCHANG, routingKey, null, count.getBytes());
                TimeUnit.MILLISECONDS.sleep(10);
            }
        } catch (Exception e) {
            LogUtil.logException(log, e);
        }
    }
}
