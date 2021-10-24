package ru.geekbrains.market.core.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.modeldtos.CategoryDto;
import ru.geekbrains.market.api.exeptions.ResourceNotFoundException;
import ru.geekbrains.market.core.services.CategoryService;
import ru.geekbrains.market.core.utils.converters.ConverterModelToDto;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService categoryService;
    private final ConverterModelToDto converterModelToDto;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return converterModelToDto.categoryToDto(categoryService.findByIdWithProducts(id).orElseThrow(() -> new ResourceNotFoundException("Category id = " + id + " not found")));
    }
}
