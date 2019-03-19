package com.xiaotian.demo.test.jvm;

public class TestConstantInit {

    public static void main(String[] args) {
        //只输出123
        System.out.println(TestConstant.name);
    }
}

class TestConstant {

    public static final String name = "123";

    static {
        System.out.println("static");
    }
}
