package com.xiaotian.demo.test.native_;

public class HelloWorld {

    public native void sayHelloWorld();

    //装入动态链接库，"HelloWorld"是要装入的动态链接库名称。
    static {
        System.loadLibrary("HelloWorld");
    }
}
