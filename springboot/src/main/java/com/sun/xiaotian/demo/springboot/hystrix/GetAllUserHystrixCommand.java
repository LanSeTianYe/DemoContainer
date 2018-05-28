package com.sun.xiaotian.demo.springboot.hystrix;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sun.xiaotian.demo.springboot.person.Person;
import com.sun.xiaotian.demo.springboot.person.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
@Scope("prototype")
public class GetAllUserHystrixCommand extends HystrixCommand<List<Person>>{

    private final static Logger logger = LogManager.getLogger(GetAllUserHystrixCommand.class);

    @Autowired
    private PersonRepository personRepository;

    public GetAllUserHystrixCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("TestConfig"));
    }

    @Override
    protected List<Person> run() throws Exception {
        logger.info("personRepository.findAll invoke");
        return personRepository.findAll();
    }

    @Override
    protected List<Person> getFallback() {
        logger.info("getFallback");
        return Collections.emptyList();
    }
}