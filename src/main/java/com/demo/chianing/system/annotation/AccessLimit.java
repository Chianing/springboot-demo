package com.demo.chianing.system.annotation;

import java.lang.annotation.*;

/**
 * 权限限制注解
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
}
