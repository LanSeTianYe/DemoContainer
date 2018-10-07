package com.sun.xiaotain.demo.springcloud.hystrix.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringCloudApplication
@EnableTurbine
public class RunHystrixTurbine {

    public static void main(String[] args) {
        SpringApplication.run(RunHystrixTurbine.class, args);
    }
}
