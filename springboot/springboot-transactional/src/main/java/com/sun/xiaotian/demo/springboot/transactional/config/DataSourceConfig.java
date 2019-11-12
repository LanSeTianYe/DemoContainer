package com.sun.xiaotian.demo.springboot.transactional.config;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;


@Slf4j
@Configuration
@EnableTransactionManagement
public class DataSourceConfig implements TransactionManagementConfigurer {

    private final static DataSource dataSource = new EncryptionPasswordDataSource();

    @Bean
    @ConfigurationProperties(ignoreUnknownFields = false, prefix = "spring.datasource.druid")
    public DataSource dataSource() {
        return dataSource;
    }

    @Bean
    @Override
    @NonNull
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
