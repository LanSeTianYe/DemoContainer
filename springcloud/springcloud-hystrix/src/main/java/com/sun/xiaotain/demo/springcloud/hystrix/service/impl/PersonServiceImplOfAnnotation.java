package com.sun.xiaotain.demo.springcloud.hystrix.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.sun.xiaotain.demo.springcloud.hystrix.model.Person;
import com.sun.xiaotain.demo.springcloud.hystrix.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service("PersonServiceImplOfAnnotation")
public class PersonServiceImplOfAnnotation implements PersonService {

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
    @HystrixCommand(defaultFallback = "oneCallBack", groupKey = "allPerson", commandKey = "getOne")
    public Person getOne() {
        return allPerson.get(random.nextInt(allPerson.size()));
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack", groupKey = "allPerson", commandKey = "getOneSlowly")
    public Person getOneSlowly() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            return null;
        }
        return getOne();
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack", groupKey = "allPerson", commandKey = "getOneRandomException")
    public Person getOneRandomException() {
        if (random.nextBoolean()) {
            throw new IllegalStateException("random exception !");
        }
        return getOne();
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack", groupKey = "allPerson", commandKey = "getById")
    @CacheResult
    public Person getById(@CacheKey int id) {
        return allPerson.get(id);
    }

    @Override
    @HystrixCommand(defaultFallback = "emptyPersonList", groupKey = "allPerson", commandKey = "getAll")
    public List<Person> getAll() {
        return Collections.unmodifiableList(allPerson);
    }

    @Override
    @HystrixCommand(defaultFallback = "oneCallBack", groupKey = "allPerson", commandKey = "updatePerson")
    @CacheRemove(commandKey = "getById")
    public Person updatePerson(int id, String name) {
        if (id >= 0 && id < allPerson.size()) {
            Person person = allPerson.get(id);
            person.setName(name);
            return person;
        }
        return oneCallBack();
    }

    @Override
    public void refreshList() {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setId(((long) i));
            person.setName("name" + i);
            person.setAge(20 + i / 2);
            allPerson.set(i, person);
        }
    }

    Person oneCallBack() {
        return Person.EmptyPerson;
    }

    List<Person> emptyPersonList() {
        return Collections.emptyList();
    }
}
