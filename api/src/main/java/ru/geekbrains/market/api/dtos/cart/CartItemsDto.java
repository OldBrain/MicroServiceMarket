package ru.geekbrains.market.api.dtos.cart;

import ru.geekbrains.market.api.dtos.modeldtos.OrderItemsDto;

import java.util.List;

public class CartItemsDto {
    private List<OrderItemsDto> items;

    public CartItemsDto(List<OrderItemsDto> items) {
        this.items = items;
    }

    public CartItemsDto() {
    }

    public List<OrderItemsDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemsDto> items) {
        this.items = items;
    }
}
