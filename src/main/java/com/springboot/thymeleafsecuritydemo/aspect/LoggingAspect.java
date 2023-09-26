package com.springboot.thymeleafsecuritydemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(* com.springboot.thymeleafsecuritydemo.controller.*.*(..))")
    public void forControllerPackage(){}
    @Pointcut("execution(* com.springboot.thymeleafsecuritydemo.dao.*.*(..))")
    public void forDaoPackage(){}
    @Pointcut("execution(* com.springboot.thymeleafsecuritydemo.service.*.*(..))")
    public void forServicePackage(){}
    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    public void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        log.info("===> in @Before: Calling method: " + method);
        Arrays.stream(joinPoint.getArgs()).sequential().forEach(arg -> log.info("===> Argument: " + arg));
    }
    @AfterReturning(pointcut = "forAppFlow()", returning = "returned")
    public void afterReturning(JoinPoint joinPoint,Object returned){
        String method = joinPoint.getSignature().toShortString();
        log.info("===> in @AfterReturning: from method: " + method);
        log.info("===> returned: " + returned);
    }
}
