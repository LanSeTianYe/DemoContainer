package com.sun.xiaotain.demo.springcloud.hystrix;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class RunHystrixDemo {

    public static void main(String[] args) {
        SpringApplication.run(RunHystrixDemo.class, args);
    }
}
