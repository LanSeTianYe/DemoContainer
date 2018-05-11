package com.sun.xiaotian.demo.springboot.person;

import java.util.List;

public interface PersonService {

    String getName();

    List<Person> getAll();

    List<Person> deleteByName(String name);

    List<Person> addAll(List<Person> personList);
}
