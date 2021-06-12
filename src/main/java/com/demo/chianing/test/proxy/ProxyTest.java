package com.demo.chianing.test.proxy;

import com.demo.chianing.test.proxy.factory.CGlibDynamicProxyFactory;
import com.demo.chianing.test.proxy.factory.DynamicProxyFactory;
import com.demo.chianing.test.proxy.factory.StaticProxyFactory;
import com.demo.chianing.test.proxy.service.ITestService;
import com.demo.chianing.test.proxy.service.impl.TestServiceImpl;

public class ProxyTest {

    public static void main(String[] args) {
        // 静态代理
        ITestService service = new StaticProxyFactory(new TestServiceImpl());
        System.out.printf("class is: %s%n", service.getClass());
        service.testMethod();

        System.out.println();

        // 动态代理
        ITestService proxyService = (ITestService) new DynamicProxyFactory(new TestServiceImpl()).getProxyInstance();
        System.out.printf("class is: %s%n", proxyService.getClass());
        proxyService.testMethod();

        System.out.println();

        // cglib动态代理
        ITestService cglibProxyService = (ITestService) new CGlibDynamicProxyFactory(new TestServiceImpl()).getProxyInstance();
        System.out.printf("class is: %s%n", cglibProxyService.getClass());
        cglibProxyService.testMethod();
    }

}
