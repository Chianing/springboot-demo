package com.demo.chianing.system.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 自定义注解切面
 */
@Slf4j
@Aspect
@Component
public class CustomAnnotationAspect {

    /**
     * 接口耗时监控 切面
     * TimeCostMonitor
     */
    @Around("@annotation(com.demo.chianing.system.annotation.TimeCostMonitor)")
    public Object doTimeCostMonitorAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        long startTime = System.currentTimeMillis();

        // 执行方法
        result = joinPoint.proceed();

        // 获取类名称
        String clazzName = joinPoint.getSignature().getDeclaringTypeName();
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();
        log.info("[print time cost] clazz: {}, method: {}, timeCost: {}ms",
                clazzName, methodName, (System.currentTimeMillis() - startTime));

        return result;
    }

}
