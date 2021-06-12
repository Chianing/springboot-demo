package com.demo.chianing.test.proxy.service.impl;

import com.demo.chianing.test.proxy.service.ITestService;

public class TestServiceImpl implements ITestService {
    @Override
    public void testMethod() {
        System.out.println("test method invoke");
    }
}
