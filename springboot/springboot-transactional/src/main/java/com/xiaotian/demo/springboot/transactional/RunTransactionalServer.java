package com.xiaotian.demo.springboot.transactional;

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
@MapperScan(basePackages = "com.xiaotian.demo.springboot.transactional.mapper")
@EnableTransactionManagement
public class RunTransactionalServer {

    private final static Logger logger = LogManager.getLogger(RunTransactionalServer.class);

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplicationBuilder()
                .sources(RunTransactionalServer.class)
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
