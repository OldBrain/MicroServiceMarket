package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;
import ru.geekbrains.trainingproject.market.model.DetailsUser;
import ru.geekbrains.trainingproject.market.model.Order;
import ru.geekbrains.trainingproject.market.model.User;
import java.util.List;

@Data
public class UserPersonalAccountDto {
    private Long id;
    private String username;
    private String email;
    private DetailsUser detailsUser;
    private List<Order> orderList;

    public UserPersonalAccountDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.detailsUser = user.getDetailsUser();
        this.orderList = user.getOrderList();
    }
}
