package com.sun.xiaotian.demo.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RunConfigClient {

    public static void main(String[] args) {
        SpringApplication.run(RunConfigClient.class, args);
    }
}
