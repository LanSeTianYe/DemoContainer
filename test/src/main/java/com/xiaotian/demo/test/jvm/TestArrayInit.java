package com.xiaotian.demo.test.jvm;

public class TestArrayInit {

    public static void main(String[] args) {
        //不输出
        TestArray[] testArray = new TestArray[1];
    }
}


class TestArray {
    static {
        System.out.println("static");
    }
}