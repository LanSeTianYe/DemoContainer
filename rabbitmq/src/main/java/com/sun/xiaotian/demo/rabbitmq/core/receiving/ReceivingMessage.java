package com.sun.xiaotian.demo.rabbitmq.core.receiving;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
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

    @RabbitListener(queues = "csdn_fans_1")
    public void processCsdnFans1(String content) {
        logger.info("csdn_fans_1 receive " +content + "...");
    }

    @RabbitListener(queues = "csdn_fans_2")
    public void processCsdnFans2(String content) {
        logger.info("csdn_fans_2 receive " +content + "...");
    }

    @RabbitListener(queues = "csdn_fans_3")
    public void processCsdnFans3(String content) {
        logger.info("csdn_fans_3 receive " +content + "...");
    }
}
