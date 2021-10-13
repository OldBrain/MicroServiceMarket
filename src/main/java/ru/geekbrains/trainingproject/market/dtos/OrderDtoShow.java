package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.trainingproject.market.dtos.OrderItemDto;
import ru.geekbrains.trainingproject.market.model.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDtoShow {
    private Long id;
    private List<OrderItemDto> items;
    private String address;
    private String phone;
    private int price;
    private String userName;

    public OrderDtoShow(Order order) {
        this.id = order.getId();
//        this.items = order.getOrdersItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.price = order.getSum();
        this.userName = order.getUser().getUsername();
    }
}
