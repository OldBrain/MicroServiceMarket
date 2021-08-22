package ru.geekbrains.trainingproject.market.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.trainingproject.market.dtos.ProductDto;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Category;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.services.CategoryService;
import ru.geekbrains.trainingproject.market.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping()
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "0", name = "p") int pageIndex) {
        if (pageIndex < 0) {
            pageIndex = 0;
        }

            return productService.findAllPage(pageIndex, 5).map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).get());
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping("/create")
    public ProductDto save(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found"));
        productService.save(product);
        return new ProductDto(product);
    }

    //http://localhost:8189/market/products/filter?minPrice=100&maxPrice=350
    //http://localhost:8189/market/products/filter?minPrice=100
    //http://localhost:8189/market/products/filter?maxPrice=350

    @GetMapping("/filter")
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
