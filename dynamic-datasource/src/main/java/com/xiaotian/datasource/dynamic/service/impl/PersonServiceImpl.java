package com.xiaotian.datasource.dynamic.service.impl;

import com.xiaotian.datasource.dynamic.entity.Person;
import com.xiaotian.datasource.dynamic.repository.PersonRepository;
import com.xiaotian.datasource.dynamic.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void insertUser(Person person) {
        personRepository.save(person);
    }
}
