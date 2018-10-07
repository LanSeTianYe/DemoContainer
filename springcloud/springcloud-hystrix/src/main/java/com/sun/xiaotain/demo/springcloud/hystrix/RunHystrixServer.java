package com.sun.xiaotain.demo.springcloud.hystrix;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@ServletComponentScan
public class RunHystrixServer {

    public static void main(String[] args) {
        SpringApplication.run(RunHystrixServer.class, args);
    }
}
