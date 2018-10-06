package com.sun.xiaotain.demo.springcloud.hystrix.service;


import com.sun.xiaotain.demo.springcloud.hystrix.model.Person;

import java.util.List;

public interface PersonService {

    Person getById(int id);

    Person getOne();

    Person getOneSlowly();

    Person getOneRandomException();

    List<Person> getAll();

    Person updatePerson(int id, String name);

    void refreshList();
}
