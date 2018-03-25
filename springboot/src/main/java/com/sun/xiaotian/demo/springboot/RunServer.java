package com.sun.xiaotian.demo.springboot;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RunServer {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplicationBuilder()
                .sources(RunServer.class)
                .listeners(new ApplicationListener<ApplicationEvent>() {
                    @Override
                    public void onApplicationEvent(ApplicationEvent event) {
                        System.out.println("触发事件: " + event);
                        if (event instanceof ApplicationReadyEvent) {
                            System.out.println("项目启动成功!");
                        }
                    }
                })
                .build();
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);

        springApplication.run(args);
//        System.exit(SpringApplication.exit(springApplication.run(args)));
    }

    @Bean
    //程序结束时返回状态
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 33;
    }

}
