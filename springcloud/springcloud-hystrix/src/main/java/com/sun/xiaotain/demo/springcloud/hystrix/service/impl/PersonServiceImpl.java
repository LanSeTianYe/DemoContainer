package com.sun.xiaotain.demo.springcloud.hystrix.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.sun.xiaotain.demo.springcloud.hystrix.model.Person;
import com.sun.xiaotain.demo.springcloud.hystrix.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class PersonServiceImpl implements PersonService {

    private final static List<Person> allPerson = new ArrayList<>();

    private final static Random random = new Random(33);

    static {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setId(((long) i));
            person.setName("name" + i);
            person.setAge(20 + i / 2);
            allPerson.add(person);
        }
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack")
    public Person getOne() {
        return allPerson.get(random.nextInt(allPerson.size()));
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack")
    public Person getOneSlowly() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            return null;
        }
        return getOne();
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack")
    public Person getOneRandomException() {
        while (random.nextBoolean()) {
            throw new IllegalStateException("random exception !");
        }
        return getOne();
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack")
    @CacheResult
    public Person getById(@CacheKey int id) {
        return allPerson.get(id);
    }

    @Override
    @HystrixCommand(defaultFallback = "emptyPersonList")
    public List<Person> getAll() {
        return Collections.unmodifiableList(allPerson);
    }

    Person oneCallBack() {
        return Person.EmptyPerson;
    }

    List<Person> emptyPersonList() {
        return Collections.emptyList();
    }
}
