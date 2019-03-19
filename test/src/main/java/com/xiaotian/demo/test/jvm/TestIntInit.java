package com.xiaotian.demo.test.jvm;

public class TestIntInit {

    public static void main(String[] args) {
        //只输出23
        System.out.println(TestInt.age);
    }
}

class TestInt {

    public static final int age = 23;

    static {
        System.out.println("static");
    }
}
