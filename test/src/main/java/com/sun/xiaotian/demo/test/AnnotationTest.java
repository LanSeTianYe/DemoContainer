package com.sun.xiaotian.demo.test;

import java.io.IOException;


public class AnnotationTest {

    private Integer testMethodAnnotation(Integer status, Person person) {
        return 100;
    }

    public Person testMethodAnnotation(String name, int age) throws IOException, NullPointerException {
        return new Person(name, age);
    }

    static class Person {

        private String name;

        private Integer age;

        private Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        AnnotationTest annotationTest = new AnnotationTest();
        Integer integer = annotationTest.testMethodAnnotation(1, new Person("xiaotian", 24));
        System.out.println(integer.intValue());
    }
}
