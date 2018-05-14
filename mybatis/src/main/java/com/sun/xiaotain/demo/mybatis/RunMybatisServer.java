package com.sun.xiaotain.demo.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sun.xiaotian.demo.mybatis.mapper")
public class RunMybatisServer {
    public static void main(String[] args) {
        SpringApplication.run(RunMybatisServer.class, args);
    }
}
