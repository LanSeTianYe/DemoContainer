package com.sun.xiaotain.demo.springcloud.hystrix.repository;

import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.sun.xiaotain.demo.springcloud.hystrix.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 模拟数据存取
 */
@Component
public class PersonRepository {
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

    public Person getOne() {
        return allPerson.get(random.nextInt(allPerson.size()));
    }

    public Person getOneSlowly() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            return null;
        }
        return getOne();
    }

    public Person getOneRandomException() {
        if (random.nextBoolean()) {
            throw new IllegalStateException("random exception !");
        }
        return getOne();
    }


    public Person getById(@CacheKey int id) {
        return allPerson.get(id);
    }

    public List<Person> getAll() {
        return Collections.unmodifiableList(allPerson);
    }

    public Person updatePerson(int id, String name) {
        if (id >= 0 && id < allPerson.size()) {
            Person person = allPerson.get(id);
            person.setName(name);
            return person;
        }
        return Person.EmptyPerson;
    }

    public void refreshList() {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setId(((long) i));
            person.setName("name" + i);
            person.setAge(20 + i / 2);
            allPerson.set(i, person);
        }
    }
}
