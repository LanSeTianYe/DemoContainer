package com.xiaotain.designpattern.core.proxy;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 代理对象生成工厂
 */
class ProxyObjectFactory {

    static <T> T getProxyObject(T object) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxyInstance = Proxy.newProxyInstance(classLoader, object.getClass().getInterfaces(), new LogInvocationHandler(object));
        return (T) proxyInstance;
    }

    static class LogInvocationHandler implements InvocationHandler {

        private final Object proxyObject;

        private Set<String> needMethod = new HashSet<>();

        LogInvocationHandler(Object proxyObject) {
            this.proxyObject = proxyObject;
            initNeedProxyMethod();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (needProxy(method.getName())) {
                System.out.println("before ...");
            }

            Parameter[] parameters = method.getParameters();
            TypeVariable<Method>[] typeParameters = method.getTypeParameters();
            for (TypeVariable<Method> typeParameter : typeParameters) {
                System.out.println(typeParameter.getName());
                System.out.println(typeParameter.getTypeName());
            }
            String paramInfo = "[";
            String split = "";
            for (int i = 0; i < parameters.length; i++) {
                paramInfo = String.format("%s%s %s %s = %s", paramInfo, split, parameters[i].getParameterizedType(), parameters[i].getName(), args[i]);
                split = ",";
            }
            paramInfo = paramInfo + "]";
            System.out.println(paramInfo);
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
