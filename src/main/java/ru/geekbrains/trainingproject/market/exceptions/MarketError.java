package ru.geekbrains.trainingproject.market.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MarketError {
    private String message;
    private Date date;

    public MarketError(String message) {
        this.message = message;
        this.date = new Date();
    }
}
