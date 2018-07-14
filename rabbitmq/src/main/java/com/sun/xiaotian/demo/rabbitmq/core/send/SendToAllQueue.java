package com.sun.xiaotian.demo.rabbitmq.core.send;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component()
@Order(1)
public class SendToAllQueue implements CommandLineRunner {

    private final static Logger logger = LogManager.getLogger(SendToAllQueue.class);

    private Long index = 1L;

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public SendToAllQueue(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
        //初始化
        try {
            FanoutExchange exchange = ((FanoutExchange) ExchangeBuilder.fanoutExchange("CSDN").build());
            amqpAdmin.declareExchange(exchange);

            Queue csdn_fans_1 = QueueBuilder.durable("csdn_fans_1").build();
            Queue csdn_fans_2 = QueueBuilder.durable("csdn_fans_2").build();
            Queue csdn_fans_3 = QueueBuilder.durable("csdn_fans_3").build();
            amqpAdmin.declareQueue(csdn_fans_1);
            amqpAdmin.declareQueue(csdn_fans_2);
            amqpAdmin.declareQueue(csdn_fans_3);

            Binding binding1 = BindingBuilder.bind(csdn_fans_1).to(exchange);
            Binding binding2 = BindingBuilder.bind(csdn_fans_2).to(exchange);
            Binding binding3 = BindingBuilder.bind(csdn_fans_3).to(exchange);

            amqpAdmin.declareBinding(binding1);
            amqpAdmin.declareBinding(binding2);
            amqpAdmin.declareBinding(binding3);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        CompletableFuture.runAsync(() -> {
            while (true) {
                try {
                    amqpTemplate.convertAndSend("CSDN", "", "" + index);
                    logger.info("SendToAllQueue send: "+ index ++);
                    TimeUnit.SECONDS.sleep(1);
                    if(index == 2 << 10) {
                        break;
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
    }
}
