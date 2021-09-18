package ru.geekbrains.trainingproject.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.trainingproject.market.model.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
}
