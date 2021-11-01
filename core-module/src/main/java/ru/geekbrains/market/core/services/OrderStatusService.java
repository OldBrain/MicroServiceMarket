package ru.geekbrains.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.core.model.OrderStatus;
import ru.geekbrains.market.core.repositories.OrderStatusRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    public Optional<OrderStatus>findTitleById(Long id) {
        return orderStatusRepository.findTitleById(id);
    }


    public OrderStatus findById(Long id) {
        return orderStatusRepository.getById(id);
    }

    public String getStatusTitleById(Long id) {
        return orderStatusRepository.getById(id).getTitle();
    }

}
