package ru.geekbrains.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.ProductDto;
import ru.geekbrains.market.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.core.exceptions.DataValidationException;
import ru.geekbrains.market.core.model.Category;
import ru.geekbrains.market.core.model.Product;
import ru.geekbrains.market.core.services.CategoryService;
import ru.geekbrains.market.core.services.ProductService;
import ru.geekbrains.market.core.utils.Converter;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    private final Converter converter;

    @GetMapping()
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "0", name = "p") int pageIndex,
                                    @RequestParam MultiValueMap<String, String> params) {
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        return productService.findAllPageByPage(pageIndex, 5, params).map(p -> converter.productToDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("product id " + id + "not found"));
        return converter.productToDto(product);
    }


    @PostMapping
    public ProductDto save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found"));
        product.setCategory(category);
        productService.save(product);
        return converter.productToDto(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProductFromDto(productDto);
    }
}
