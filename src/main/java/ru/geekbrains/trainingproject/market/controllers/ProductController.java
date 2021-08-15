package ru.geekbrains.trainingproject.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @GetMapping("/products/filter")
    public List<Product> findAllByPriceIsBetween(
            @RequestParam(name = "minPrice", required = false) Integer minPrice,
            @RequestParam(name = "maxPrice", required = false) Integer maxPrice) {
        if (minPrice == null & maxPrice == null) {
            return productService.findAll();
        }

        if (minPrice != null & maxPrice == null) {
            return productService.findAllByPriceIsMoreThenEqual(minPrice);
        }
        if (minPrice == null & maxPrice != null) {
            return productService.findAllByPriceLessThanEqual(maxPrice);
        }
        return productService.findAllByPriceIsBetween(minPrice, maxPrice);
    }
}
