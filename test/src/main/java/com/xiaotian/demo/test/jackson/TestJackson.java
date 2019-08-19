package com.xiaotian.demo.test.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;

import java.io.IOException;
import java.util.Map;

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

        Map person1 = mapper.readValue("<Person><name></name><age>24</age><sex>2</sex><ative>true</ative><_UnSafe>_UnSafe</_UnSafe></Person>", Map.class);
        System.out.println(person1.toString());
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
