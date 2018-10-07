package com.sun.xiaotain.demo.springcloud.hystrix.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.sun.xiaotain.demo.springcloud.hystrix.model.Person;
import com.sun.xiaotain.demo.springcloud.hystrix.repository.PersonRepository;
import com.sun.xiaotain.demo.springcloud.hystrix.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service("PersonServiceImplOfAnnotation")
public class PersonServiceImplOfAnnotation implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImplOfAnnotation(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack", groupKey = "allPerson", commandKey = "getOne")
    public Person getOne() {
        return personRepository.getOne();
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack", groupKey = "allPerson", commandKey = "getOneSlowly")
    public Person getOneSlowly() {
        return personRepository.getOneSlowly();
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack", groupKey = "allPerson", commandKey = "getOneRandomException")
    public Person getOneRandomException() {
        return personRepository.getOneRandomException();
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack", groupKey = "allPerson", commandKey = "getById")
    @CacheResult
    public Person getById(@CacheKey int id) {
        return personRepository.getById(id);
    }

    @Override
    @HystrixCommand(defaultFallback = "emptyPersonList", groupKey = "allPerson", commandKey = "getAll")
    public List<Person> getAll() {
        return personRepository.getAll();
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack", groupKey = "allPerson", commandKey = "updatePerson")
    @CacheRemove(commandKey = "getById")
    public Person updatePerson(int id, String name) {
        personRepository.updatePerson(id, name);
        return oneCallBack();
    }

    @Override
    public void refreshList() {
        personRepository.refreshList();
    }

    Person oneCallBack() {
        return Person.EmptyPerson;
    }

    List<Person> emptyPersonList() {
        return Collections.emptyList();
    }
}
