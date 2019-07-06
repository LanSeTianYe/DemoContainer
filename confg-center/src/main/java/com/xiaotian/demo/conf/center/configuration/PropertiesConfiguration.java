package com.xiaotian.demo.conf.center.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PropertiesConfiguration implements CommandLineRunner, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("name:{}", environment.getProperty("name"));
        log.info("conf1 name:{}", environment.getProperty("conf1.name"));
        log.info("conf2 name:{}", environment.getProperty("conf2.name"));
    }
}
