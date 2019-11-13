package com.xiaotian.demo.springboot.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "{com.sun.xiaotian.demo.springboot.demo.*}")
@ServletComponentScan
@EnableAsync
public class RunServer {

    private final static Logger logger = LogManager.getLogger(RunServer.class);

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplicationBuilder()
                .sources(RunServer.class)
                .listeners((event) -> {
                            logger.debug(event.toString());
                            if (event instanceof ApplicationReadyEvent) {
                                logger.info("项目启动成功");
                            }
                        }
                )
                .build();
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);

        springApplication.run(args);
    }

    @Bean
    //程序结束时返回状态
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 33;
    }

}
