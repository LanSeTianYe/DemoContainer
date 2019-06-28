package com.xiaotian.datasource.dynamic;

import com.xiaotian.datasource.dynamic.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunServer {

    private final PersonService personService;

    public RunServer(PersonService personService) {
        this.personService = personService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RunServer.class);
    }
}
