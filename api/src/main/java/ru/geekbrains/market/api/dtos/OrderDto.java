package ru.geekbrains.market.api.dtos;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private List<OrderItemDto> items;
    private String address;
    private String phone;
    private BigDecimal price;

    private String last_name;
    private String patronymic;
    private String first_name;
    private Long orderStatus;

    public Long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Long orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public OrderDto() {
    }

    public OrderDto(Long id, List<OrderItemDto> items, String address, String phone, BigDecimal price, String last_name, String patronymic, String first_name,Long orderStatus) {
        this.id = id;
        this.items = items;
        this.address = address;
        this.phone = phone;
        this.price = price;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.first_name = first_name;
        this.orderStatus = orderStatus;
    }
}
