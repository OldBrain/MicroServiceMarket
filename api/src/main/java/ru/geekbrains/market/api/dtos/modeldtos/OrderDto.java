package ru.geekbrains.market.api.dtos.modeldtos;


import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private Long id;
    private String lastName;
    private String patronymic;
    private String firstName;
    private String phone;
    private String address;
    private Integer sum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
    private List<OrderItemsDto> ordersItems;
    private OrderStatusDto orderStatus;

    public OrderDto() {
    }

    public OrderDto(Long id, String lastName, String firstName, String patronymic, Integer sum , String phone, String address, LocalDateTime createdAt, LocalDateTime updatedAt, Long userId, List<OrderItemsDto> ordersItems, OrderStatusDto orderStatus) {
        this.id = id;
        this.lastName = lastName;
        this.sum = sum;
        this.patronymic = patronymic;
        this.firstName = firstName;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.ordersItems = ordersItems;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemsDto> getOrdersItems() {
        return ordersItems;
    }

    public void setOrdersItems(List<OrderItemsDto> ordersItems) {
        this.ordersItems = ordersItems;
    }

    public OrderStatusDto getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusDto orderStatus) {
        this.orderStatus = orderStatus;
    }
}
