package ru.geekbrains.market.core.utils.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.market.api.dtos.modeldtos.*;
import ru.geekbrains.market.core.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConverterModelToDto {

    public ProductDto productToDto(Product p) {
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice(), p.getCategory().getTitle());
    }

    public UserPersonalAccountDto userPersonalAccountToDto(User user) {
        return new UserPersonalAccountDto(user.getId(), user.getUsername(),
                user.getEmail(), detailsUserToDto(user.getDetailsUser()));
    }

    public CategoryDto categoryToDto(Category category) {
        List<ProductDto> productDtoList = category.getProducts().stream().map(p -> productToDto(p)).collect(Collectors.toList());
        return new CategoryDto(category.getId(), category.getTitle(), productDtoList);
    }

    public OrderDto orderToDto(Order o) {

        return new OrderDto(o.getId(), o.getLastName(), o.getFirstName(),
                o.getPatronymic(), o.getSum(), o.getPhone(), o.getAddress(),
                o.getCreatedAt(), o.getUpdatedAt(), o.getUser().getId(),
                o.getOrdersItems().stream().map(oi -> orderItemsToDto(oi)).collect(Collectors.toList()),
                orderStatusToDto(o.getOrderStatus()));
    }

    public OrderStatusDto orderStatusToDto(OrderStatus orderStatus) {
        return new OrderStatusDto(orderStatus.getId(), orderStatus.getTitle());
    }

    public List<OrderDto> orderFromUserToDto(User user) {
        return user.getOrderList().stream().map(o -> orderToDto(o)).collect(Collectors.toList());
    }

    public OrderItemsDto orderItemsToDto(OrderItems oi) {
        return new OrderItemsDto(oi.getId(), oi.getProductTitle(), oi.getQuantity(), oi.getPricePerProduct(), oi.getPrice());
    }

    public DetailsUserDto detailsUserToDto(DetailsUser du) {
        return new DetailsUserDto(du.getLastName(), du.getFirstName(), du.getPatronymic(), du.getPhone(), du.getAddress());
    }

}
