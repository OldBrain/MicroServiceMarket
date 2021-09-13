package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.trainingproject.market.model.User;
import ru.geekbrains.trainingproject.market.model.DetailsUser;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private DetailsUser detailsUser;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.detailsUser = user.getDetailsUser();
    }
}
