package ru.geekbrains.market.core.utils;

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

    @Around("execution(public * ru.geekbrains.market.core.services..*.*(..))" +
            "//execution(public * ru.geekbrains.market.core.services..*.*())")

    public Object TimeWorkingServices(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String className = proceedingJoinPoint.getSignature().getDeclaringType().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;

        statisticDto.setServiceMap(className,methodName,duration);

//        System.out.println("ИТОГ: "+statisticDto.getServiceMap().entrySet());
        return out;
    }


}

