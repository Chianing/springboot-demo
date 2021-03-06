package com.demo.chianing.system.annotation;

import java.lang.annotation.*;

/**
 * 耗时监控注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeCostMonitor {
}
