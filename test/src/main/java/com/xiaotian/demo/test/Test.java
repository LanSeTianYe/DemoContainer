package com.xiaotian.demo.test;

public class Test {

    public static void main(String[] args) {
        System.out.print("Hello Java");

        Animal animal = new Animal("animal");
        sayHello(animal);

        Person person = new Person("zhang san");
        person.Hello();
        sayHello(person);
    }

    static class Animal {
        String name;

        public Animal(String name) {
            this.name = name;
        }

        public void Hello() {
            System.out.println("Hello, I am " + name);
        }
    }

    static class Person extends Animal {
        public Person(String name) {
            super(name);
        }
    }

    static void sayHello(Animal animal) {
        animal.Hello();
    }
}



