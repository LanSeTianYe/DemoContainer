package com.sun.xiaotian.demo.springboot.transactional.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author sunfeilong   (sunfeilong@eglsgame.com)
 * @version V1.0
 * @date 2019/9/26 20:38
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(ignoreUnknownFields = false, prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        return new EncryptionPasswordDataSource();
    }
}
