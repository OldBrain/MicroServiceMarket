package ru.geekbrains.market.core.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.modeldtos.ProductDto;
import ru.geekbrains.market.api.exeptions.ResourceNotFoundException;
import ru.geekbrains.market.core.exceptions.DataValidationException;
import ru.geekbrains.market.core.model.Category;
import ru.geekbrains.market.core.model.Product;
import ru.geekbrains.market.core.services.CategoryService;
import ru.geekbrains.market.core.services.ProductService;
import ru.geekbrains.market.core.utils.converters.ConverterModelToDto;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ConverterModelToDto converterModelToDto;



    @GetMapping()

    public Page<ProductDto> findAll(@RequestParam(defaultValue = "0", name = "p") int pageIndex) {
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        return productService.findAllPageByPage(pageIndex, 5).map(p -> converterModelToDto.productToDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("product id " + id + "not found"));
        return converterModelToDto.productToDto(product);
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
        return converterModelToDto.productToDto(product);
    }

    @PutMapping("")
    public void productDtoEdit(@RequestBody ProductDto productDto) {
        productService.updateProductFromDto(productDto);
    }


}
