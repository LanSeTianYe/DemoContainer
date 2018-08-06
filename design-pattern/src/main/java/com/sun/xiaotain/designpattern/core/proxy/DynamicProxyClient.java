package com.sun.xiaotain.designpattern.core.proxy;

import sun.reflect.Reflection;

public class DynamicProxyClient {

    public static void main(String[] args) {
        ProxyObjectFactory<DoSomething> proxyObjectFactory = new ProxyObjectFactory<>();
        DoSomething doSomething = proxyObjectFactory.getProxyObject(new Human());
        doSomething.doSomething();
        System.out.println(doSomething.toString());
    }
}
