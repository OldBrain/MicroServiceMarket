package ru.geekbrains.market.api.dtos.modeldtos;


import java.util.List;

public class UserPersonalAccountDto {
    private Long id;
    private String username;
    private String email;
    private DetailsUserDto detailsUserDto;
//    private List<OrderDto> orderList;

    public UserPersonalAccountDto() {
    }

    public UserPersonalAccountDto(Long id, String username, String email, DetailsUserDto detailsUserDto) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.detailsUserDto = detailsUserDto;
//        this.orderList = orderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DetailsUserDto getDetailsUserDto() {
        return detailsUserDto;
    }

    public void setDetailsUserDto(DetailsUserDto detailsUserDto) {
        this.detailsUserDto = detailsUserDto;
    }

//    public List<OrderDto> getOrderList() {
//        return orderList;
//    }

//    public void setOrderList(List<OrderDto> orderList) {
//        this.orderList = orderList;
//    }
}
