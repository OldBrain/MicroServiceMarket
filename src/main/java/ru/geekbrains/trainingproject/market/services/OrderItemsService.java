package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.model.OrderItems;
import ru.geekbrains.trainingproject.market.repositories.OrderItemsRepository;

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
