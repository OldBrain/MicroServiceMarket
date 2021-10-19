package ru.geekbrains.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.core.model.OrderItems;
import ru.geekbrains.market.core.repositories.OrderItemsRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    public Optional<OrderItems> getOrderItemsById(Long id) {
        return orderItemsRepository.findById(id);
    }
    public void Save(OrderItems orderItems) {
        orderItemsRepository.save(orderItems);
    }
}
