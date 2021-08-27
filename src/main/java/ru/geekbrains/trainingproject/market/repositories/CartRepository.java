package ru.geekbrains.trainingproject.market.repositories;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.geekbrains.trainingproject.market.dtos.CartProductDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Data
@Repository
public class CartRepository {
    List<CartProductDto> cartProductsDtoList=new LinkedList<>();

}
