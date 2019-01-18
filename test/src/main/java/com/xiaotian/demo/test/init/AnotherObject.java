package com.xiaotian.demo.test.init;

public class AnotherObject {

    /**
     * 静态代码块儿在类被虚拟机加载时会执行
     */
    static {
        System.out.println("AnotherObject static code block");
    }

    public static void printName() {
        System.out.println("AnotherObject ");
    }
}
