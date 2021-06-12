package com.demo.chianing.test.proxy.factory;

import com.demo.chianing.test.proxy.service.ITestService;

public class StaticProxyFactory implements ITestService {

    private final ITestService target;

    public StaticProxyFactory(ITestService target) {
        this.target = target;
    }

    @Override
    public void testMethod() {
        System.out.println("---static proxy start");
        target.testMethod();
        System.out.println("---static proxy end");
    }
}
