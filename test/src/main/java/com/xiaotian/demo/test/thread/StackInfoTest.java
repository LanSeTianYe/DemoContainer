package com.xiaotian.demo.test.thread;

public class StackInfoTest {

    public static void main(String[] args) {
        StackInfoTest stackInfoTest = new StackInfoTest();
        stackInfoTest.method1();
    }

    private void method1() {
        printStackInfo();
    }

    private void printStackInfo() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        System.out.println("-------------------------------------------------------");
        System.out.println("ClassName: " + stackTraceElement.getClassName());
        System.out.println("MethodName: " + stackTraceElement.getMethodName());
        System.out.println("lineNumber: " + stackTraceElement.getLineNumber());
        System.out.println(String.format("%s.%s [%s] invoke exception", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), stackTraceElement.getLineNumber()));
    }
}

