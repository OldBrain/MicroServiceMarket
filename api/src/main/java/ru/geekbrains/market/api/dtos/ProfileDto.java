package ru.geekbrains.market.api.dtos;

import java.util.List;

public class ProfileDto {
    private String username;
    //    private String firstName;
//    private String lastName;
    private String email;
    private List<DetailsUserDto> detailsUserDtoList;


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

    public ProfileDto() {
    }

    public List<DetailsUserDto> getDetailsUserDtoList() {
        return detailsUserDtoList;
    }

    public void setDetailsUserDtoList(List<DetailsUserDto> detailsUserDtoList) {
        this.detailsUserDtoList = detailsUserDtoList;
    }

    public ProfileDto(String username, String email, List<DetailsUserDto> detailsUserDtoList) {
        this.username = username;
        this.email = email;
        this.detailsUserDtoList = detailsUserDtoList;
    }
}