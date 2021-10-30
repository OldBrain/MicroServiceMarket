package ru.geekbrains.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.core.model.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {

}
