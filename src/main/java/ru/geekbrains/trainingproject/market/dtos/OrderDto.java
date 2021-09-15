package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.trainingproject.market.model.User;
import ru.geekbrains.trainingproject.market.model.DetailsUser;
import ru.geekbrains.trainingproject.market.utils.Cart;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String username;
    private String email;
    private DetailsUser detailsUser;
    private List<CartItemDto> items;
    private Integer totalPrice;

    public OrderDto(Cart cart, User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.detailsUser = user.getDetailsUser();
        this.items = cart.getItemListByUserName(username);
        this.totalPrice = cart.getTotalPrice();
    }
}
