package com.sun.xiaotian.demo.springboot.demo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sun.xiaotian.demo.springboot.demo.person.Person;
import com.sun.xiaotian.demo.springboot.demo.person.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Scope("prototype")
public class GetAllUserHystrixCommand extends HystrixCommand<List<Person>>{

    private final static Logger logger = LogManager.getLogger(GetAllUserHystrixCommand.class);

    private final PersonRepository personRepository;

    public GetAllUserHystrixCommand(PersonRepository personRepository) {
        super(HystrixCommandGroupKey.Factory.asKey("TestConfig"));
        this.personRepository = personRepository;
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
