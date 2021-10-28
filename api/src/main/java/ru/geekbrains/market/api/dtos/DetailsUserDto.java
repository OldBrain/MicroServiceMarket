package ru.geekbrains.market.api.dtos;

public class DetailsUserDto {
    private String lastName;
    private String firstName;
    private String patronymic;
    private String phone;
    private String address;

    public DetailsUserDto() {
    }

    public DetailsUserDto(String lastName, String firstName, String patronymic, String phone, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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
}
