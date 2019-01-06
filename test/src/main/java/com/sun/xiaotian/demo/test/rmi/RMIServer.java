package com.sun.xiaotian.demo.test.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.TimeUnit;

/**
 * 远程方法调用服务端
 */
public class RMIServer {

    public static void main(String[] args) throws RemoteException, MalformedURLException, InterruptedException {
        UUIDInterfaceImpl uuidInterface = new UUIDInterfaceImpl();

        LocateRegistry.createRegistry(8888);
        Naming.rebind("rmi://127.0.0.1:8888/userInfoService1", uuidInterface);
        System.out.println("RMIServer running ...");
        TimeUnit.SECONDS.sleep(100);
    }
}
