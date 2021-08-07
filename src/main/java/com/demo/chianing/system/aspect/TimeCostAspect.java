package com.demo.chianing.system.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeCostAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.demo.chianing.system.annotation.TimeCostMonitor)")
    public void getTimeCost() {
    }

    @Around("getTimeCost()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object result = null;

        long startTime = System.currentTimeMillis();

        try {
            // 执行方法
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("catch throwAble: ", e);
        }

        // 获取类名称
        String clazzName = joinPoint.getSignature().getDeclaringTypeName();
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();

        log.info("[Time cost monitor] clazz: {}, method: {}, timeCost: {}ms",
                clazzName, methodName, (System.currentTimeMillis() - startTime));

        return result;
    }

}
