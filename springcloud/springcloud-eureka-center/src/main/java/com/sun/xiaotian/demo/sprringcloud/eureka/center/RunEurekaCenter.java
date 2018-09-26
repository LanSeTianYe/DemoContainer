package com.sun.xiaotian.demo.sprringcloud.eureka.center;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RunEurekaCenter {

    public static void main(String[] args) {
        SpringApplication.run(RunEurekaCenter.class, args);
    }
}
