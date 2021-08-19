package ru.geekbrains.trainingproject.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.trainingproject.market.dtos.ProductDto;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "0",name = "p") int pageIndex ) {
        if (pageIndex < 0) {
            pageIndex = 0;
        }

        return productService.findAllPage(pageIndex, 5).map(ProductDto::new);
    }



    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).get());
    }


    @GetMapping("/products/del/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping("/create")
    public List<Product> save(Product product) {
        productService.save(product);
        return productService.findAll();
    }

    //http://localhost:8189/market/products/filter?minPrice=100&maxPrice=350
    //http://localhost:8189/market/products/filter?minPrice=100
    //http://localhost:8189/market/products/filter?maxPrice=350

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
