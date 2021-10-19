package ru.geekbrains.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.core.model.OrderStatus;
import ru.geekbrains.market.core.repositories.OrderStatusRepository;

@Service
@RequiredArgsConstructor
public class OrderStatusService {
    public final OrderStatusRepository orderStatusRepository;
    public OrderStatus getOrderStatusById(Long id) {
        return orderStatusRepository.getById(id);
    }
}
