package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;

import java.util.Map;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class MapResponse {

    private Map mapValue;
//    private List<String[]> keys;
//    private List<Long> time;

    public MapResponse(Map value) {
        this.mapValue = value;
    }

}
