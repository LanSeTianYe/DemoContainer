package com.xiaotian.demo.log.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RunLogBackDemo {

    public static void main(String[] args) {
        log.info("start run application ... ...");
//        LoggerContext loggerContext = ((LoggerContext) LoggerFactory.getILoggerFactory());
//        StatusPrinter.print(loggerContext);
        SpringApplication.run(RunLogBackDemo.class, args);
    }
}
