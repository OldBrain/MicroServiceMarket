package ru.geekbrains.trainingproject.market.dtos.security;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.geekbrains.trainingproject.market.utils.TimeAspect;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class StatisticDto {
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Long>> serviceMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Long> tmpMethodMap = new ConcurrentHashMap<>();

    public void setServiceMap(String serviceName, String methodName,Long duration) {
        //если уже были обе записи
        if (serviceMap.containsKey(serviceName) && serviceMap.get(serviceName).containsKey(methodName)) {
            Long tmpDuration = serviceMap.get(serviceName).get(methodName) + duration;
            serviceMap.get(serviceName).put(methodName, tmpDuration);
        }

//сли новый сервис
        if (!serviceMap.containsKey(serviceName))  {
            tmpMethodMap.put(methodName, duration);
            serviceMap.put(serviceName, tmpMethodMap);
            tmpMethodMap.clear();
        }

        //если бла запись о сервисе но новый метод
        if (serviceMap.containsKey(serviceName) && !serviceMap.get(serviceName).containsKey(methodName)) {
            serviceMap.get(serviceName).put(methodName, duration);
        }
    }
}
