package ru.geekbrains.trainingproject.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class MapResponse {

    private Map value;
    private List<String[]> keys;
//    private List<Long> time;

    public MapResponse(Map value) {
        this.value = value;
    }

}
