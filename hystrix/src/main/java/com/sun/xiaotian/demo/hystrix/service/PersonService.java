package com.sun.xiaotian.demo.hystrix.service;

import com.sun.xiaotian.demo.hystrix.model.Person;
import reactor.core.publisher.Mono;

public interface PersonService {

    Mono<Person> getById(String id);
}
