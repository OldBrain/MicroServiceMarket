package ru.geekbrains.trainingproject.market.controllers.v1;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.trainingproject.market.dtos.MapResponse;
import ru.geekbrains.trainingproject.market.utils.StatisticUtil;

@RestController
@RequestMapping("/api/v1/stat")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticUtil statisticDto;

    @GetMapping()
    public MapResponse getServiceMethodStatistic() {
//        return  new MapResponse(timeAspect.getMethodTimeMap());
//        System.out.println("Передаю: "+timeAspect.getServiceMap());
        return  new MapResponse(statisticDto.getServiceMap());
    }
}
