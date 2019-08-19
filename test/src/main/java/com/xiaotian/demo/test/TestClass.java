package com.xiaotian.demo.test;

public class TestClass {

    public static void main(String[] args) {

        Human human = new Human();
        System.out.println(human.getName());
        System.out.println(human.getDescription());
    }
}

interface Name {
    String getName();
}

abstract class Animal implements Name {

    public abstract String getDescription();

}

class Human extends Animal {

    @Override
    public String getName() {
        return "Human";
    }

    @Override
    public String getDescription() {
        return "I ma a Human!";
    }
}
