package com.demo.chianing.test.proxy.factory;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGlibDynamicProxyFactory implements MethodInterceptor {

    private final Object target;

    public CGlibDynamicProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        Enhancer result = new Enhancer();
        result.setSuperclass(target.getClass());
        result.setCallback(this);
        return result.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("---cglib dynamic proxy start");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("---cglib dynamic proxy end");
        return result;
    }

}
