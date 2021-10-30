package ru.geekbrains.market.api.dtos;

public class OrderStatusDto {
    private Long id;
    String title;

    public OrderStatusDto() {
    }

    public OrderStatusDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
