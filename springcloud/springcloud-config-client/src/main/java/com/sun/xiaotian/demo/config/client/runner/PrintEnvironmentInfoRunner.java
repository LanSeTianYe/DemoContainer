package com.sun.xiaotian.demo.config.client.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RefreshScope
public class PrintEnvironmentInfoRunner implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(PrintEnvironmentInfoRunner.class);

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            while (true) {
                logger.info(environment.getProperty("index"));
                logger.info(environment.getProperty("branch"));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }).start();
    }
}
