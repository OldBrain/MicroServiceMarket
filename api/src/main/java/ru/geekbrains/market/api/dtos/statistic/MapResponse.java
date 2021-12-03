package ru.geekbrains.market.api.dtos.statistic;


import java.util.Map;

public class MapResponse {

    private Map mapValue;

    public MapResponse(Map mapValue) {
        this.mapValue = mapValue;
    }

    public Map getMapValue() {
        return mapValue;
    }

    public void setMapValue(Map mapValue) {
        this.mapValue = mapValue;
    }
}
