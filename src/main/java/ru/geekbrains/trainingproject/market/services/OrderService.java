package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.dtos.CartItemDto;
import ru.geekbrains.trainingproject.market.dtos.OrderDto;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Order;
import ru.geekbrains.trainingproject.market.model.OrderItems;
import ru.geekbrains.trainingproject.market.model.Role;
import ru.geekbrains.trainingproject.market.repositories.OrderRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    public final OrderRepository orderRepository;
    public final OrderItemsService orderItemsService;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
    public void createAndSaveOrder(OrderDto orderDto) {
        Order order = createOrderFromOrderDto(orderDto);
        saveOrder(order);

    }

    private Order createOrderFromOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.setFirst_name(orderDto.getDetailsUser().getFirstName());
        order.setLast_name(orderDto.getDetailsUser().getLastName());
        order.setPatronymic(orderDto.getDetailsUser().getPatronymic());
        order.setSum(orderDto.getTotalPrice());
        order.setPhone(orderDto.getDetailsUser().getPhone());
        order.setAddress(orderDto.getDetailsUser().getAddress());

        Optional<OrderItems> orderItemsOptional = orderItemsService.getOrderItemsById(order.getId());
        OrderItems orderItems = orderItemsOptional.orElseThrow(()->new ResourceNotFoundException("Нет списка продуктов для заказа № "+order.getId()));
        order.setOrdersItems(Collections.singletonList(orderItems));

       // Начальный статус заказа сформирован
        order.setStatus_id(0l);

        return order;
    }

}
