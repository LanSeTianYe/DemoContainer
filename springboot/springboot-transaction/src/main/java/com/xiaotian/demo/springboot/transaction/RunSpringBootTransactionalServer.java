package com.xiaotian.demo.springboot.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.xiaotian.demo.springboot.transaction.mapper")
@EnableTransactionManagement
public class RunSpringBootTransactionalServer {

    private final static Logger logger = LogManager.getLogger(RunSpringBootTransactionalServer.class);

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplicationBuilder()
                .sources(RunSpringBootTransactionalServer.class)
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
