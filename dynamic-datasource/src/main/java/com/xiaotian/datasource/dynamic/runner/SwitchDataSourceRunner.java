package com.xiaotian.datasource.dynamic.runner;

import com.xiaotian.datasource.dynamic.datasource.ThreadDataSourceNameHolder;
import com.xiaotian.datasource.dynamic.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SwitchDataSourceRunner implements CommandLineRunner {

    private final PersonService personService;

    public SwitchDataSourceRunner(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... args) {
        ThreadDataSourceNameHolder.set("test1");
        log.info("switch datasource in runner, {}", personService.getAll());
        ThreadDataSourceNameHolder.set("test2");
        log.info("switch datasource in runner, {}", personService.getAll());
    }
}
