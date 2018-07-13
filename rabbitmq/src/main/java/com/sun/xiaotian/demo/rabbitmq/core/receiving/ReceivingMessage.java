package com.sun.xiaotian.demo.rabbitmq.core.receiving;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceivingMessage {

    private final static Logger logger = LogManager.getLogger(ReceivingMessage.class);

    @RabbitListener(queues = "odd_queue")
    public void processOddNumber(String content) {
        logger.info("ood number receive " +content + "...");
    }

    @RabbitListener(queues = "even_queue")
    public void processEvenNumber(String content) {
        logger.info("even number receive " +content + "...");
    }
}
