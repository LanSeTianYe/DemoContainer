package com.sun.xiaotian.demo.test.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 使用远程服务
 */
public class User {

    private static final UUIDInterface uuidInterface2;

    static {
        uuidInterface2 = new UUIDInterface() {
            private long uuid = 0;

            @Override
            public long get() {
                uuid = uuid + 2;
                return uuid;
            }
        };
    }


    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, AlreadyBoundException {
        //将本地服务注册到RMI服务端
        try {
            Naming.lookup("rmi://192.168.1.22:8888/userInfoService2");
        } catch (NotBoundException e) {
            Naming.bind("rmi://192.168.1.22:8888/userInfoService2", uuidInterface2);
        }

        //remote object 远程对象，方法调用会直接调用服务端方法
        UUIDInterface uuidInterface1 = (UUIDInterface) Naming.lookup("rmi://192.168.1.22:8888/userInfoService1");
        //non remote object 非远程对象，方法调用不会直接调用服务端方法
        UUIDInterface uuidInterface2 = (UUIDInterface) Naming.lookup("rmi://192.168.1.22:8888/userInfoService2");
        for (
                int i = 0;
                i < 10; i++) {
            System.out.println(uuidInterface1.get());
            System.out.println(uuidInterface2.get());
            System.out.println();
        }
    }
}
