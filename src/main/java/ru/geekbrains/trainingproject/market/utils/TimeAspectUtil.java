package ru.geekbrains.trainingproject.market.utils;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Data
public class TimeAspectUtil {
    private final StatisticUtil statisticDto;

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

        statisticDto.setServiceMap(className,methodName,duration);

//        System.out.println("ИТОГ: "+statisticDto.getServiceMap().entrySet());
        return out;
    }


}

