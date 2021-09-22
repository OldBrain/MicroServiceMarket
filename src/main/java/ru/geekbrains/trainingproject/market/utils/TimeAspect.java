package ru.geekbrains.trainingproject.market.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class TimeAspect {
    private Map<String, Map<String, Long>> serviceTimeMap = new HashMap<>();
    private Map<String, Long> methodTimeMap = new HashMap<>();

    @Around("execution(public * ru.geekbrains.trainingproject.market.services.*.*(..))" +
            "//execution(public * ru.geekbrains.trainingproject.market.services.*.*())")

    public Object TimeWorkingServices(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

       String signature = proceedingJoinPoint.getSignature().toShortString();
       String[] fulPath = signature.split("\\.");
        String className = fulPath[0];
        String methodName = fulPath[1];


        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;

        if (serviceTimeMap.containsKey(className)) {
            if (methodTimeMap.containsKey(methodName)) {

                methodTimeMap.put(methodName, methodTimeMap.get(methodName) + duration);
                serviceTimeMap.put(className, methodTimeMap);
            }
        } else {
            methodTimeMap.put(methodName, duration);
            serviceTimeMap.put(className, methodTimeMap);
        }
        System.out.println(serviceTimeMap.toString());
        return out;
    }

    public Map<String, Map<String, Long>> getServiceTimeMap() {
        return serviceTimeMap;
    }

    public Map<String, Long> getMethodTimeMap() {
        return methodTimeMap;
    }
}
