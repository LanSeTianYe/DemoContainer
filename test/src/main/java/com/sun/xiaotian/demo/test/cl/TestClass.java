package com.sun.xiaotian.demo.test.cl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TestClass extends ArrayList implements List, Cloneable, Serializable {

    private static final Consumer<Object> consoleConsumer = System.out::println;

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        consoleConsumer.accept("");
        consoleConsumer.accept("当前类: ");
        consoleConsumer.accept(testClass.getClass());

        List<Class> parentClass = getParentClass(testClass);
        consoleConsumer.accept("");
        consoleConsumer.accept("父类: ");
        parentClass.forEach(consoleConsumer);


        consoleConsumer.accept("");
        consoleConsumer.accept("实现的接口: ");
        getInterfaces(testClass).forEach(consoleConsumer);
    }

    private static List<Class> getParentClass(Object object) {
        List<Class> results = new ArrayList<>();
        Class<?> superclass = object.getClass().getSuperclass();
        while (null != superclass){
            results.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return results;
    }

    private static List<Class> getInterfaces(Object object) {
        List<Class> results = new ArrayList<>();
        for (Class<?> anInterface : object.getClass().getInterfaces()) {
            results.add(anInterface);
            getInterfaces(anInterface, results);
        }
        return results;
    }

    private static void getInterfaces(Class clazz, List<Class> interfacesList) {
        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            interfacesList.add(anInterface);
            getInterfaces(anInterface, interfacesList);
        }
    }
}
