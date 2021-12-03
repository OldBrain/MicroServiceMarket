package ru.geekbrains.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.core.model.Category;
import ru.geekbrains.market.core.model.OrderStatus;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
    Optional<OrderStatus> findTitleById(Long id);



    //    @Query("select OrderStatus .title from Order where OrderStatus .id = :id")
//    Optional<OrderStatus> findTitleById(Long id);
}
