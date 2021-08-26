package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Сart {
    private Long id;
    private String categoryTitle;
    private String title;
    private Integer price;
    private Integer quantity;
}
