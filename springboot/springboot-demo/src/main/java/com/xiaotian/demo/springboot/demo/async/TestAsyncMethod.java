package com.xiaotian.demo.springboot.transactional.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TestAsyncMethod {

    private final static Logger logger = LogManager.getLogger(TestAsyncMethod.class);

    @Async
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("TestAsyncMethod run finished after 5 second ...");
    }
}
