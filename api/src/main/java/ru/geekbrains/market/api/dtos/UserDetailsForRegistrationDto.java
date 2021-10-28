package ru.geekbrains.market.api.dtos;


public class UserDetailsForRegistrationDto {
    private String username;
    private String password;
    private String email;
    private DetailsUserDto detailsUser;

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

    public DetailsUserDto getDetailsUser() {
        return detailsUser;
    }

    public void setDetailsUser(DetailsUserDto detailsUser) {
        this.detailsUser = detailsUser;
    }
}
