package com.sun.xiaotian.demo.hystrix.service.impl;

import com.sun.xiaotian.demo.hystrix.model.Person;
import com.sun.xiaotian.demo.hystrix.service.PersonService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public Mono<Person> getById(String id) {
        return Mono.just(new Person());
    }
}
