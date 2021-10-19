package ru.geekbrains.market.core.utils.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.market.api.dtos.modeldtos.*;
import ru.geekbrains.market.core.model.*;
import ru.geekbrains.market.core.services.UserService;

@Component
@RequiredArgsConstructor
public class ConverterDtoToModel {
    public final UserService userService;

    public DetailsUser detailsUserFromDto(DetailsUserDto dud) {
        DetailsUser detailsUser = new DetailsUser();
        detailsUser.setFirstName(dud.getFirstName());
        detailsUser.setLastName(dud.getLastName());
        detailsUser.setPatronymic(dud.getPatronymic());
        detailsUser.setPhone(dud.getPhone());
        detailsUser.setAddress(dud.getAddress());
        return detailsUser;
    }

    public Order orderFromOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.setFirstName(orderDto.getFirstName());
        order.setLastName(orderDto.getLastName());
        order.setPatronymic(orderDto.getPatronymic());
        order.setSum(orderDto.getSum());
        order.setPhone(orderDto.getPhone());
        order.setAddress(orderDto.getAddress());
        order.setUser(userService.getUserById(orderDto.getUserId()).get());
        return order;
    }
}
