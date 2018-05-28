package com.sun.xiaotian.demo.test;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric<V> {

    public boolean setValue(V text) {
        return true;
    }

    public V getValue() {
        return ((V) new ArrayList<>());
    }

    public static void main(String[] args) {
        //没有指定泛型类型，默认是Object
        TestGeneric testGeneric = new TestGeneric();
        boolean value = testGeneric.setValue(new Object());

        //指定泛型类型
        TestGeneric<String> setTypeGeneric = new TestGeneric();
        boolean value1 = setTypeGeneric.setValue("");

//        //类型转换出错
//        TestGeneric<String> getValueStr = new TestGeneric<>();
//        String value2 = getValueStr.getValue();
//        System.out.println(value2);

        TestGeneric objectTestGeneric = new TestGeneric();
        Object value3 = objectTestGeneric.getValue();
        System.out.println(value3);

        TestGeneric test = new TestGeneric();
        List value2 = (List) test.getValue();
        System.out.println(value2);
    }
}
