package ru.geekbrains.trainingproject.market.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.trainingproject.market.dtos.ProductDto;
import ru.geekbrains.trainingproject.market.exceptions.DataValidationException;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Category;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.services.CategoryService;
import ru.geekbrains.trainingproject.market.services.ProductService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return productService.findAllPageByPage(pageIndex, 5).map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("product id " + id + "not found"));
        return new ProductDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("product id " + id + "not found"));
        productService.deleteProductById(id);
    }

    @PostMapping("")
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
        return new ProductDto(product);
    }

    @PutMapping("")
    public void productDtoEdit(@RequestBody ProductDto productDto) {
        productService.updateProductFromDto(productDto);
    }



}
