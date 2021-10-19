package ru.geekbrains.market.api.dtos.cart;

import ru.geekbrains.market.api.dtos.modeldtos.OrderItemsDto;

import java.util.List;


public class CartDto {
    private List<OrderItemsDto> items;
    private int totalPrice;

    public List<OrderItemsDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemsDto> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDto() {
    }

    public CartDto(List<OrderItemsDto> items, int totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }
}
