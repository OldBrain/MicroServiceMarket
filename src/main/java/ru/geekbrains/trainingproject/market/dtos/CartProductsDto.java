package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.trainingproject.market.model.Product;

@Component
@Data
@NoArgsConstructor
public class CartProductsDto extends ProductDto {
    private Integer quantity;

    public CartProductsDto(Product product) {
        super(product);
    }
}
