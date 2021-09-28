package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.trainingproject.market.model.DetailsUser;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class DetailsUserDto {
    private String lastName;
    private String firstName;
    private String patronymic;
    private String phone;
    private String address;

    public DetailsUserDto(DetailsUser detailsUser) {
        this.lastName = detailsUser.getLastName();
        this.firstName = detailsUser.getFirstName();
        this.patronymic = detailsUser.getPatronymic();
        this.phone = detailsUser.getPhone();
        this.address = detailsUser.getAddress();
    }
}
