package com.xiaotian.demo.test.cl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException, NoSuchMethodException, InvocationTargetException {
        //确保target目录加载到Simple.Class文件,如果有的话父类加载器会加载到类文件
        FileSystemClassLoader fileSystemClassLoader1 = new FileSystemClassLoader("D:\\");
        FileSystemClassLoader fileSystemClassLoader2 = new FileSystemClassLoader("D:\\");


        Class<?> simpleCLass1 = fileSystemClassLoader1.loadClass("com.xiaotian.demo.test.cl.Simple");
        Class<?> simpleCLass2 = fileSystemClassLoader2.loadClass("com.xiaotian.demo.test.cl.Simple");
        System.out.println(simpleCLass1.getClassLoader());
        System.out.println(simpleCLass2.getClassLoader());
        TimeUnit.SECONDS.sleep(1);

        Object simple1 = simpleCLass1.newInstance();
        Object simple2 = simpleCLass2.newInstance();

        Method setInstance = simpleCLass1.getMethod("setInstance", Object.class);
        setInstance.invoke(simple1, simple2);
    }
}
