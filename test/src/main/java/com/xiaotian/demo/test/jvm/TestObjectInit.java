package com.xiaotian.demo.test.jvm;

public class TestObjectInit {

    public static void main(String[] args) {
        //输出 static 123
        System.out.println(TestObject.name);
    }
}

class TestObject {

    public static final String name = new String("123");

    static {
        System.out.println("static");
    }
}