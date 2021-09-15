package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class OrderItemDto {
    private List<CartItemDto> items;
//    private int totalPrice;
}
