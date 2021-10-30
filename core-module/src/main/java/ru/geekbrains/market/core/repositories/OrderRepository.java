package ru.geekbrains.market.core.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.core.model.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.username = :username")
    @EntityGraph(value = "orders.for-front")
    List<Order> findAllByUsername(String username);

    Optional<Order> findOneByIdAndUsername(Long id, String username);

}
