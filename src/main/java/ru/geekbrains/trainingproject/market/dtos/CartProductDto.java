package ru.geekbrains.trainingproject.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.trainingproject.market.model.Product;

@Data
@NoArgsConstructor
public class CartProductDto {
    private Long id;
    private String title;
    private Integer price;
    private String categoryTitle;
    private Integer quantity;

    public CartProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.quantity = 0;
        this.categoryTitle = product.getCategory().getTitle();
    }
}
