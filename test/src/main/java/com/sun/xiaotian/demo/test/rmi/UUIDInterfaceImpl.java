package com.sun.xiaotian.demo.test.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UUIDInterfaceImpl extends UnicastRemoteObject implements UUIDInterface {

    private long number = 0;

    protected UUIDInterfaceImpl() throws RemoteException {
        super();
    }

    @Override
    public long get() throws RemoteException {
        System.out.println("UUIDInterfaceImpl_get_invoke ...");
        number = number + 1;
        return number;
    }
}
