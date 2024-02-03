package com.nhnacademy.edu.springframework.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class MethodAspect {

    @Pointcut("@annotation(com.nhnacademy.edu.springframework.annotation.Dooray) && execution(* com.nhnacademy.edu.springframework.sender.DoorayMessageSender.sendMessage(..))")
    public void messageSendService(){}

    @Around("messageSendService()")
    public Object timeLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();
        stopWatch.stop();

        log.info("[{}].[{}] [{}] ms",joinPoint.getTarget().getClass().getSimpleName() ,joinPoint.getSignature().getName() ,stopWatch.getTotalTimeMillis());

        return result;
    }
}
