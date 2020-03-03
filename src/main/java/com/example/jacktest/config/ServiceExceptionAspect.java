package com.example.jacktest.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceExceptionAspect {

    @Around("execution(* com.example.jacktest.services.*.*(..))")
    public Object serviceExceptionHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            return joinPoint.proceed();
        }catch(Throwable e){
            //서비스 로직 에러를 RuntimeException으로 컨트롤러에 전달.
            throw new RuntimeException(e);
        }
    }
}
