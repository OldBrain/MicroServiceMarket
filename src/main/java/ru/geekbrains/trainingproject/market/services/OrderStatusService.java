package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.model.OrderStatus;
import ru.geekbrains.trainingproject.market.repositories.OrderStatusRepository;

@Service
@RequiredArgsConstructor
public class OrderStatusService {
    public final OrderStatusRepository orderStatusRepository;
    public OrderStatus getOrderStatusById(Long id) {
        return orderStatusRepository.getById(id);
    }
}
