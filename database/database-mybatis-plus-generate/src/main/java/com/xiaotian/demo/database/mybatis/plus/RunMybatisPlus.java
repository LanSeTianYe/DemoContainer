package com.xiaotian.demo.database.mybatis.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaotian.demo.database.mybatis.plus.system.mapper")
public class RunMybatisPlus {

    public static void main(String[] args) {
        SpringApplication.run(RunMybatisPlus.class);
    }
}
