package com.xiaotian.demo.lambda.example;

public class Test {

    public static void main(String[] args) {
        ManContainer manContainer = new ManContainer();
        manContainer.accept(new Man());
//        manContainer.accept(new Human());
        manContainer.accept(new GreenMan());
        
    }

    static class Container<T extends Man> {
        void accept(T t) {

        }
    }

    static class ManContainer extends Container<Man> {
        void accept(Man t) {

        }
    }


    static class Human {

    }

    static class Man extends Human {

    }

    static class GreenMan extends Man {

    }
}


