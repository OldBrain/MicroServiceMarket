package ru.geekbrains.market.api.dtos.security;


import ru.geekbrains.market.api.dtos.modeldtos.DetailsUserDto;

public class UserDetailsForRegistrationDto {
    private String username;
    private String password;
    private String email;
    private DetailsUserDto detailsUserDto;

    public UserDetailsForRegistrationDto() {
    }

    public UserDetailsForRegistrationDto(String username, String password, String email, DetailsUserDto detailsUserDto) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.detailsUserDto = detailsUserDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setDetailsUser(DetailsUserDto detailsUser) {
        this.detailsUserDto = detailsUser;
    }
}
