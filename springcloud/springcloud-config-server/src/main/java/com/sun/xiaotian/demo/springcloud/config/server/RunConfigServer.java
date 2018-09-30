package com.sun.xiaotian.demo.springcloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class RunConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(RunConfigServer.class, args);
    }
}
