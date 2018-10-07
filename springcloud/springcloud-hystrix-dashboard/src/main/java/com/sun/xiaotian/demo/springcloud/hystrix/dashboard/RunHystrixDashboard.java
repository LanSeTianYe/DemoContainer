package com.sun.xiaotian.demo.springcloud.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringCloudApplication
@EnableHystrixDashboard
public class RunHystrixDashboard {

    public static void main(String[] args) {
        SpringApplication.run(RunHystrixDashboard.class, args);
    }
}
