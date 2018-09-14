package com.sun.xiaotian.demo.test;

import java.beans.beancontext.BeanContext;
import java.util.Iterator;
import java.util.ServiceLoader;

public class TestServiceLoader {

    public static void main(String[] args) {
        ServiceLoader<BeanContext> drivers = ServiceLoader.load(BeanContext.class);
        Iterator<BeanContext> driverIterator = drivers.iterator();
        while (driverIterator.hasNext()) {
            System.out.println(driverIterator.next().getClass().getName());
        }
    }
}
