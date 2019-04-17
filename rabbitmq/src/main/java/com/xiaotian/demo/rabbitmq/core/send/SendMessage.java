package com.xiaotian.demo.rabbitmq.core.send;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
@Order(2)
public class SendMessage implements CommandLineRunner {

    private final static Logger logger = LogManager.getLogger(SendMessage.class);

    private Long index = 1L;

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public SendMessage(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
        //初始化
        Exchange numberEx = ExchangeBuilder.directExchange("number_ex").build();

        amqpAdmin.declareExchange(numberEx);
        Queue oddQueue = QueueBuilder.durable("odd_queue").build();
        Queue evenQueue = QueueBuilder.durable("even_queue").build();
        amqpAdmin.declareQueue(oddQueue);
        amqpAdmin.declareQueue(evenQueue);
        Binding odd = BindingBuilder.bind(oddQueue).to(numberEx).with("odd").noargs();
        Binding even = BindingBuilder.bind(evenQueue).to(numberEx).with("even").noargs();
        amqpAdmin.declareBinding(odd);
        amqpAdmin.declareBinding(even);
    }

    @Override
    public void run(String... args) throws Exception {

        CompletableFuture.runAsync(() -> {
            while (true) {
                try {
                    long start;
                    if (index % 2 == 0) {
                        start = System.currentTimeMillis();
                        amqpTemplate.convertAndSend("number_ex", "even", index);
                        logger.info("even send cost time: " + (System.currentTimeMillis() - start));
                    } else {
                        start = System.currentTimeMillis();
                        amqpTemplate.convertAndSend("number_ex", "odd", index);
                        logger.info("odd send cost time: " + (System.currentTimeMillis() - start));
                    }
                    logger.info(String.format("send index: %s ...", index++));
                    TimeUnit.MILLISECONDS.sleep(1000L);
                    if (index == 1 << 10) {
                        break;
                    }
                } catch (Exception e) {
                    logger.error("send", e);
                }
            }
        });
    }
}
