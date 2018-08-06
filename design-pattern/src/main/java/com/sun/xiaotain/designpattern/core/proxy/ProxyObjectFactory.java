package com.sun.xiaotain.designpattern.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 代理对象生成工厂
 *
 * @param <T> 代理类的接口
 */
class ProxyObjectFactory<T> {

    T getProxyObject(T object) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxyInstance = Proxy.newProxyInstance(classLoader, object.getClass().getInterfaces(), new LogInvocationHandler(object));
        return (T) proxyInstance;
    }

    class LogInvocationHandler implements InvocationHandler {

        private final Object proxyObject;

        private Set<String> needMethod = new HashSet<>();

        LogInvocationHandler(Object proxyObject) {
            this.proxyObject = proxyObject;
            initNeedProxyMethod();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(needProxy(method.getName())) {
                System.out.println("before ...");
            }
            Object result = method.invoke(proxyObject, args);
            if (needProxy(method.getName())) {
                System.out.println("after ...");
            }
            return result;
        }

        private boolean needProxy(String name) {
            return needMethod.contains(name);
        }

        private void initNeedProxyMethod() {
            Arrays.stream(proxyObject.getClass().getInterfaces()).forEach(i -> {
                for (Method method : i.getMethods()) {
                    needMethod.add(method.getName());
                }
            });
            needMethod.forEach(System.out::println);
        }
    }
}
