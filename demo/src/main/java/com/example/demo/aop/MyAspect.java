package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

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

//        Class clazz = joinPoint.getTarget().getClass();
//        Logger logger = LoggerFactory.getLogger(clazz);
//
//        String url = getRequestUrl(joinPoint, clazz);
//        String payload = getPayload(joinPoint);
//        logger.info("POST " + url + " Payload " + payload);


    }

    private String getRequestUrl(JoinPoint joinPoint, Class clazz) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PostMapping methodPostMapping = method.getAnnotation(PostMapping.class);
        RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
        return getPostUrl(requestMapping, methodPostMapping);
    }

    private String getPayload(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = signature.getParameterNames()[i];
            builder.append(parameterName);
            builder.append(": ");
            builder.append(joinPoint.getArgs()[i].toString());
            builder.append(", ");
        }
        return builder.toString();
    }
    private String getPostUrl(RequestMapping requestMapping, PostMapping postMapping) {
        String baseUrl ="";
        String endpoint ="";
        if(Objects.nonNull(requestMapping)){getUrl(requestMapping.value());}
        if(Objects.nonNull(postMapping)){getUrl(postMapping.value());}

        return baseUrl + endpoint;
    }

    private String getUrl(String[] urls) {
        if (urls.length == 0) return "";
        else return urls[0];
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

//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(parameterMap);
//        System.out.println("-----------------REQUEST------------------");
//        System.out.println("request parameter: " + json);

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
