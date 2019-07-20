package com.xiaotian.demo.log.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunLogBackDemo {

    private final static Logger logger = LoggerFactory.getLogger(RunLogBackDemo.class);

    public static void main(String[] args) {
        logger.info("system start running ... ...");
        SpringApplication.run(RunLogBackDemo.class, args);
    }
}
