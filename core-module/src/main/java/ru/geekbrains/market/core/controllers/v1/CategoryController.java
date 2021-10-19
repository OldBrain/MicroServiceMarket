package ru.geekbrains.market.core.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.market.api.dtos.modeldtos.CategoryDto;
import ru.geekbrains.market.api.exeptions.ResourceNotFoundException;
import ru.geekbrains.market.core.services.CategoryService;
import ru.geekbrains.market.core.utils.converters.ConverterModelToDto;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ConverterModelToDto converterModelToDto;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return converterModelToDto.categoryToDto(categoryService.findByIdWithProducts(id).orElseThrow(() -> new ResourceNotFoundException("Category id = " + id + " not found")));
    }
}
