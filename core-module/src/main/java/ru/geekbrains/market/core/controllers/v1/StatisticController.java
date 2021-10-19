package ru.geekbrains.market.core.controllers.v1;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.market.api.dtos.responses.MapResponse;
import ru.geekbrains.market.core.utils.StatisticUtil;

@RestController
@RequestMapping("/api/v1/stat")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticUtil statisticDto;

    @GetMapping()
    public MapResponse getServiceMethodStatistic() {
        return  new MapResponse(statisticDto.getServiceMap());
    }
}
