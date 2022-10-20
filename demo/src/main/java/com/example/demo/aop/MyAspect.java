package com.example.demo.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    @Pointcut("execution(* com.example.demo..*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("=====before advice starts=====");
        System.out.println(getMethodName(joinPoint));
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        System.out.println("=====before advice ends=====");
    }
    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("=====after advice starts=====");
        System.out.println("=====after advice ends=====");
    }
    
    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("=====after returning advice starts=====");
        if (result != null) {
            System.out.println(result);
        }
        System.out.println("=====after returning advice ends=====");
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=====around advice starts=====");
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println("Time spent: " + spentTime);
        System.out.println("=====around advice ends=====");

        return result;
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
    public void afterThorwing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("=====after throwing advice starts=====");
        System.out.println(getMethodName(joinPoint));
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        System.out.println(throwable.getMessage());
        System.out.println("=====after throwing advice ends=====");
    }
    private String getMethodName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getName();
    }
}
