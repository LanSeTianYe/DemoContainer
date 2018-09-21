package com.sun.xiaotian.demo.springcloud.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringApplicationContext {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationContext.class, args);
    }

}
