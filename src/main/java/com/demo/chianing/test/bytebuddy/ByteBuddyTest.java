package com.demo.chianing.test.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ByteBuddyTest {

    public static void main(String[] args) throws Exception {
        replaceToString();
        // addNewClass();
    }

    /**
     * 重写toString方法
     */
    private static void replaceToString() throws Exception {
        Class<?> clazz = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("byte buddy test"))
                .make()
                // 加载该class
                .load(ByteBuddyTest.class.getClassLoader()).getLoaded();

        System.out.printf("toString result is: %s%n", clazz.newInstance());
    }

    /**
     * 添加类
     */
    private static void addNewClass() throws Exception {
        Class<?> clazz = new ByteBuddy()
                .subclass(Object.class)
                // 添加类
                .name("NewClass")
                // 添加方法
                .defineMethod("testMethod", String.class, Modifier.PUBLIC)
                .intercept(MethodDelegation.to(ByteBuddyTest.class))
                // 添加属性
                .defineField("testField", String.class, Modifier.PUBLIC)
                .make()
                .load(ByteBuddyTest.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        Method testMethod = clazz.getDeclaredMethod("testMethod", (Class<?>) null);
        testMethod.invoke(clazz.newInstance());

    }

}
