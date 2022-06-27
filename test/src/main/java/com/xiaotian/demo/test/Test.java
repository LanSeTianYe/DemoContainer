package com.xiaotian.demo.test;

public class Test {

    public static void main(String[] args) {
        System.out.println("Hello Java");

        Animal animal = new Animal("Animal");
        animal.sayName();

        Person person = new Person("Person");
        person.sayName();

        Test.sayHello(animal);
        Test.sayHello(person);
    }

    static class Animal {
        private final String name;

        Animal(String name) {
            this.name = name;
        }

        public void sayName() {
            System.out.println("animal name");
        }
    }


    static class Person extends Animal {

        Person(String name) {
            super(name);
        }

        @Override
        public void sayName() {
            System.out.println("person name");
        }
    }

    private static void sayHello(Animal animal) {
        animal.sayName();
    }

}



