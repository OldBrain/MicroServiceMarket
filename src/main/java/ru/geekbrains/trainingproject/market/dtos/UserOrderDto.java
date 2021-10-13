package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.trainingproject.market.model.Order;
import ru.geekbrains.trainingproject.market.model.OrderItems;
import ru.geekbrains.trainingproject.market.model.User;

import java.util.List;
@Data
@NoArgsConstructor
public class UserOrderDto {
    private List<Order> orderList;

//    private List<OrderItems> ordersItems;
    public UserOrderDto(User user) {
        this.orderList = user.getOrderList();

        System.out.println("**************"+this.orderList.toString());

    }
}
