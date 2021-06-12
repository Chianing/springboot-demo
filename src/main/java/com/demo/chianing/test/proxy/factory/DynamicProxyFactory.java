package com.demo.chianing.test.proxy.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyFactory {

    private final Object target;

    public DynamicProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("---dynamic proxy start");
                Object result = method.invoke(target, args);
                System.out.println("---dynamic proxy end");
                return result;
            }
        });
    }

}
