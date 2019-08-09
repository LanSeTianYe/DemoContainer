package com.xiaotian.demo.test.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;

import java.io.IOException;

public class TestJackson {

    private static final ObjectMapper mapper = new XmlMapper();

    public static void main(String[] args) throws IOException {
        Person person = new Person();
        person.name = "";
        person.age = 24;
        person.sex = 2;
        person.ative = true;
        person._UnSafe = "_UnSafe";
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person));

        Person person1 = mapper.readValue("<Person><name></name><age>24</age><sex>2</sex><ative>true</ative><_UnSafe>_UnSafe</_UnSafe></Person>", Person.class);
        System.out.println(person1);
    }

    @Data
    static class Person {
        String name;
        int age;
        int sex;
        boolean ative;
        String _UnSafe;
    }
}
