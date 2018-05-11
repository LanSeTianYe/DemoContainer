package com.sun.xiaotian.demo.springboot.runner;

import com.sun.xiaotian.demo.springboot.async.TestAsyncMethod;
import com.sun.xiaotian.demo.springboot.person.Person;
import com.sun.xiaotian.demo.springboot.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Order(1)
@Component
public class TestCommandLineRunner implements CommandLineRunner {

    private final TestAsyncMethod testAsyncMethod;

    private final PersonService personService;

    @Autowired
    public TestCommandLineRunner(TestAsyncMethod testAsyncMethod, PersonService personService) {
        this.testAsyncMethod = testAsyncMethod;
        this.personService = personService;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("TestCommandLineRunner start");
        testAsyncMethod.run();
        ArrayList<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setName("name" + i);
            personList.add(person);
        }
        personService.addAll(personList);
        System.out.println("TestCommandLineRunner end");
    }
}
