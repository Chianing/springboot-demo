package com.demo.chianing.system.aspect;

import com.demo.chianing.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 通用切面
 */
@Slf4j
@Aspect
@Component
public class CommonAspect {

    /**
     * controller日志 切面
     * 记录耗时、方法入参等信息
     * <p>
     * execution(<修饰符模式>？<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)
     */
    @Around("execution(public com.demo.chianing.system.http.Response com.demo.chianing.controller..*(..))")
    public Object doPrintLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        long startTime = System.currentTimeMillis();

        result = joinPoint.proceed();
        String clazzName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("[invoke controller method] clazz: {}, method: {}, costTime: {}ms, args: {}",
                clazzName, methodName, (System.currentTimeMillis() - startTime), GsonUtil.toJsonString(args));

        return result;

    }

}
