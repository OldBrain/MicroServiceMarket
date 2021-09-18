package ru.geekbrains.trainingproject.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.trainingproject.market.model.Order;
import ru.geekbrains.trainingproject.market.model.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

}
