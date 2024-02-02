package com.nhnacademy.edu.springframework.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

@Slf4j
public class MethodAspect {
    public Object timeLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();
        stopWatch.stop();

        log.info("[{}].[{}] [{}] ms",joinPoint.getTarget().getClass().getSimpleName() ,joinPoint.getSignature().getName() ,stopWatch.getTotalTimeMillis());

        return result;
    }
}
