package com.sun.xiaotain.designpattern.core.proxy;

public class DynamicProxyClient {

    public static void main(String[] args) {
        Human human = new Human();
        ShowInfo doSomething = ProxyObjectFactory.getProxyObject(human);
        doSomething.showInfo("XiaoTian", 25);
        System.out.println(doSomething.toString());
    }
}
