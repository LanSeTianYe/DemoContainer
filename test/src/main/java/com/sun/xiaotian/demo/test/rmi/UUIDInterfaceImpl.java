package com.sun.xiaotian.demo.test.rmi;

public class UUIDInterfaceImpl implements UUIDInterface {

    private long number = 0;

    @Override
    public long get() {
        System.out.println("UUIDInterfaceImpl_get_invoke ...");
        number = number + 1;
        return number;
    }
}
