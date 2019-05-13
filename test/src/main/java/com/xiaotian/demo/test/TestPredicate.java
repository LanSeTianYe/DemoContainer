package com.xiaotian.demo.test;

import java.util.function.Predicate;

public class TestPredicate {

    public static void main(String[] args) {
        String firstName = "first";
        String lastName = "last";

        Predicate<String> firstNameEql = firstName::equals;
        Predicate<String> lastNameEql = lastName::equals;

        System.out.println(firstNameEql.or(lastNameEql).test("first"));
        System.out.println(firstNameEql.or(lastNameEql).test("last"));
        System.out.println(firstNameEql.or(lastNameEql).test("test"));
    }
}
