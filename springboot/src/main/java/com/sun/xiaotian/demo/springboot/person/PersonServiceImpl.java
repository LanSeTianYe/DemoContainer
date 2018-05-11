package com.sun.xiaotian.demo.springboot.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Value("${project.name}")
    private String projectName;

    @Value("${number}")
    private int number;

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public String getName() {
        return projectName + " " + number + " ";
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public List<Person> deleteByName(String name) {
        return personRepository.deleteByName(name);
    }

    @Override
    public List<Person> addAll(List<Person> personList) {
        return personRepository.saveAll(personList);
    }
}
