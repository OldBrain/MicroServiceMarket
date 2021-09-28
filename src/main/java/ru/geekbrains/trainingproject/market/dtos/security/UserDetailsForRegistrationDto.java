package ru.geekbrains.trainingproject.market.dtos.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.trainingproject.market.model.DetailsUser;

@Data
@NoArgsConstructor
public class UserDetailsForRegistrationDto {
    private String username;
    private String password;
    private String email;
    private DetailsUser detailsUser;
}
