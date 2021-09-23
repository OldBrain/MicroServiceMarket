package ru.geekbrains.trainingproject.market.controllers.v1;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.trainingproject.market.dtos.MapResponse;
import ru.geekbrains.trainingproject.market.utils.TimeAspect;

@RestController
@RequestMapping("/api/v1/stat")
@RequiredArgsConstructor
public class StatisticController {
    private final TimeAspect timeAspect;

    @GetMapping()
    public MapResponse getServiceMethodStatistic() {
//        return  new MapResponse(timeAspect.getMethodTimeMap());
        return  new MapResponse(timeAspect.getServiceMap());
    }
}
