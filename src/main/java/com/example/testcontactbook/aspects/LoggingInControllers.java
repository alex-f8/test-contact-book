package com.example.testcontactbook.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingInControllers {

    @Around("execution(* com.example.testcontactbook.controllers.*.*(..))")
    public Object loggingInControllers(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName().toLowerCase();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("\n\n>>> (request)\naction: {}-{} \nargs: {} ", className, methodName, methodArgs);
        Object resp = joinPoint.proceed();
        log.info("\n\n<<< (response)\naction: {}-{} \nresp-data: {}\n", className, methodName, resp);
        return resp;
    }
}
