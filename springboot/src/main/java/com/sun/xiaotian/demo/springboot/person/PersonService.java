package com.sun.xiaotian.demo.springboot.person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    String getName();

    List<Person> getAll();

    List<Person> deleteByName(String name);

    List<Person> addAll(List<Person> personList);

    Optional<Person> findByPersonId(Long personId);
}
