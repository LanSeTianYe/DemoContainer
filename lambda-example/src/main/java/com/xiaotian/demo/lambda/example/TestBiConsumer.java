package com.xiaotian.demo.lambda.example;

import java.util.function.BiConsumer;

/**
 * @author sunfeilong [2020/4/6 16:52]
 */
public class TestBiConsumer {

    public static void main(String[] args) {
        String name = "XiaoTian";
        Integer age = 27;

        TestBiConsumer testBiConsumer = new TestBiConsumer();
        testBiConsumer.consumer(testBiConsumer::printNameAndAge, name, age);

    }

    public void consumer(BiConsumer<String, Integer> biConsumer, String name, Integer age) {
        biConsumer.accept(name, age);
    }

    public void printNameAndAge(String name, Integer age) {
        String message = String.format("%s's age is %s.", name, age);
        System.out.println(message);
    }
}
