package ru.geekbrains.trainingproject.market.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.trainingproject.market.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findAllByPriceIsBetween(Integer minPrice, Integer maxPrice);

    Optional<Product> findAllByPriceLessThanEqual(Integer maxPrice);

    Optional<Product> findProductByTitleEquals(String title);

    @Query("select p from Product p where p.price>=:minPrice")
    Optional<Product> findAllByPriceIsMoreThenEqual(Integer minPrice);


}
