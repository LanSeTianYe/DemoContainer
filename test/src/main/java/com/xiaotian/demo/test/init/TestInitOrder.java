package com.xiaotian.demo.test.init;

public class TestInitOrder {

    /**
     * 静态代码块儿在类被虚拟机加载时会执行
     */
    static {
        System.out.println("static code block ...");
    }

    /**
     * 代码块儿在类初始化时构造函数之前执行
     */
    {
        System.out.println("code block run ...");
    }

    public TestInitOrder() {
        System.out.println("construct function run ...");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //只引用 AnotherObject 静态代码块儿不会执行
        System.out.println(AnotherObject.class);
        //加载 AnotherObject 静态代码块儿执行
        //Class<?> aClass = Class.forName(AnotherObject.class.getName());
        TestInitOrder testInitOrder = new TestInitOrder();
    }
}
