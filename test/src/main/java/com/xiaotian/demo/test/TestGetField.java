package com.xiaotian.demo.test;


import java.util.Arrays;

public class TestGetField {

    public static void main(String[] args) {
        System.out.println("fields:");
        Class<ChildInnerClass> cl = ChildInnerClass.class;
        Arrays.stream(cl.getFields()).forEach(System.out::println);
        System.out.println("declare fields:");
        Arrays.stream(cl.getDeclaredFields()).forEach(System.out::println);
        Class<? super ChildInnerClass> superclass = cl.getSuperclass();
        Arrays.stream(superclass.getDeclaredFields()).forEach(System.out::println);
    }
}

class InnerClass {
    private String name;
    public int version;
}

class ChildInnerClass extends InnerClass {

}
