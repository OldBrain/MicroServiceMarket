package ru.geekbrains.trainingproject.market.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class TimeAspect {
    private Map<String, Map<String, Long>> serviceMap = new HashMap<>();
    private Map<String, Long> methodMap = new HashMap<>();

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

        //TO DO
        //Переделать метод

        if (serviceMap.containsKey(className)) {
            if (methodMap.containsKey(methodName)) {
               Long newDuration=null;
               Map<String,Long> tmpMethodMap=serviceMap.get(className);
                newDuration = tmpMethodMap.get(methodName) + duration;
                tmpMethodMap.put(methodName, newDuration);
                serviceMap.put(className, tmpMethodMap);
                tmpMethodMap.clear();
            }
        } else {
            serviceMap.put(className, methodMap);
            methodMap.put(methodName, duration);
        }
        System.out.println(serviceMap.toString());
        return out;
    }

    public Map<String, Map<String, Long>> getServiceMap() {
        return serviceMap;
    }

    public Map<String, Long> getMethodMap() {
        return methodMap;
    }
}
