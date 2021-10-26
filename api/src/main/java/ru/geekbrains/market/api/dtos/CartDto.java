package ru.geekbrains.market.api.dtos;

import java.util.List;

public class CartDto {
    private List<OrderItemDto> items;
    private int totalPrice;

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
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

    public CartDto(List<OrderItemDto> items, int totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }
}
