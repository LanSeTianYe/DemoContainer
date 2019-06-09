package com.xiaotian.demo.test.collection;

import java.util.EnumSet;

public class TestEnumSet {

    public static void main(String[] args) {
        EnumSet<Fruit> fruitSet = EnumSet.allOf(Fruit.class);
        System.out.println(fruitSet.contains(Fruit.Apple));
    }

    private enum Fruit {
        Apple,
        Banana,
        Lemon,
        Watermelon
    }
}
