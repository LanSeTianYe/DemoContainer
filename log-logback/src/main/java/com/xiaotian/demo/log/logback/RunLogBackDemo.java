package com.xiaotian.demo.log.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RunLogBackDemo {

    public static void main(String[] args) {
        log.info("start run application ... ...");
        SpringApplication.run(RunLogBackDemo.class, args);
    }
}
