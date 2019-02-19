package com.xiaotian.demo.test;

import com.xiaotian.demo.test.spi.NumberInterface;

import java.util.Iterator;
import java.util.ServiceLoader;

public class TestSPI {

    public static void main(String[] args) throws ClassNotFoundException {
        ServiceLoader<NumberInterface> load = ServiceLoader.load(NumberInterface.class);
        Iterator<NumberInterface> iterator = load.iterator();
        if (iterator.hasNext()) {
            NumberInterface numberInterface = iterator.next();
            System.out.println(numberInterface.getName());
        }
    }
}
