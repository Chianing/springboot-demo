package com.demo.chianing.test.proxy;

import com.demo.chianing.test.proxy.service.ITestService;

public class StaticProxy implements ITestService {

    private final ITestService target;

    public StaticProxy(ITestService target) {
        this.target = target;
    }

    @Override
    public void testMethod() {
        System.out.println("---static proxy start");
        target.testMethod();
        System.out.println("---static proxy end");
    }
}
