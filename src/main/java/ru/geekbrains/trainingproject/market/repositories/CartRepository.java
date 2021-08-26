package ru.geekbrains.trainingproject.market.repositories;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.trainingproject.market.dtos.CartProductsDto;
import ru.geekbrains.trainingproject.market.model.Product;

import java.util.List;

@Repository
@Data
public class CartRepository {
    List<CartProductsDto> cartProductsDtoList;



}
