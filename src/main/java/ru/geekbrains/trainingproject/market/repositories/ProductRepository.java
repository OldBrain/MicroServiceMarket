package ru.geekbrains.trainingproject.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.trainingproject.market.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceIsBetween(Integer minPrice, Integer maxPrice);

    List<Product> findAllByPriceLessThanEqual(Integer maxPrice);

    @Query("select p from Product p where p.price>=:minPrice")
    List<Product> findAllByPriceIsMoreThenEqual(Integer minPrice);
}
