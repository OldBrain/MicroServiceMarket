package ru.geekbrains.trainingproject.market.utils;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Data
public class TimeAspect {
    private Map<String, Map<String, Long>> serviceMap = new HashMap<>();
    private Map<String, Long> tmpMethodMap = new HashMap<>();

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

        setServiceMap(className,methodName,duration);


        return out;
    }

    private void setServiceMap(String serviceName, String methodName,Long duration) {
        if (serviceMap.containsKey(serviceName)) {
            if (serviceMap.get(serviceName).containsKey(methodName)) {
                tmpMethodMap.clear();
                Long tmpDuration=0l;
                tmpMethodMap = serviceMap.get(serviceName);
                if (tmpMethodMap.get(methodName) == null) {
                    tmpMethodMap.put(methodName,0l);
                }
                tmpDuration = tmpMethodMap.get(methodName) + duration;
                tmpMethodMap.put(methodName, tmpDuration);
                serviceMap.put(serviceName, tmpMethodMap);
                System.out.println("?????????????????????????????????????");
                System.out.println("** Старая мапа: "+serviceMap.toString());
            } else {
                tmpMethodMap.clear();
                tmpMethodMap = serviceMap.get(serviceName);
                tmpMethodMap.put(methodName, duration);
                serviceMap.put(serviceName, tmpMethodMap);
                System.out.println("/////////////////////////////////////");
                System.out.println("** Старая мапа с новым методом: "+serviceMap.toString());

            }
        } else {
            tmpMethodMap.clear();
            tmpMethodMap.put(methodName, duration);
            serviceMap.put(serviceName, tmpMethodMap);
            System.out.println("*************************************");
            System.out.println("** Новая мапа: "+serviceMap.toString());
        }
    }

//    public Map<String, Map<String, Long>> getServiceMap() {
//        return serviceMap;
//    }

}
