package com.sun.xiaotian.demo.springbootadmin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootClientApplication.class, args);
    }

}
