package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.trainingproject.market.dtos.ProductDto;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Category;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }


public Page<Product> findAllPageByPage(int pageIndex, int pageSize) {
    return productRepository.findAll(PageRequest.of(pageIndex,pageSize));

}

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }


    public Optional<Product> findAllByPriceIsBetween(Integer minPrise, Integer maxPrice) {
        return productRepository.findAllByPriceIsBetween(minPrise,maxPrice);
    }


    public Optional<Product> findAllByPriceLessThanEqual(Integer maxPrice) {
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }

    public Optional<Product> findAllByPriceIsMoreThenEqual(Integer minPrice) {
        return productRepository.findAllByPriceIsMoreThenEqual(minPrice);
    }

    @Transactional
    public void updateProductFromDto(ProductDto productDto) {
        Product product = findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found"));
        if (!product.getCategory().getTitle().equals(productDto.getCategoryTitle())) {
            Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(
                    ()-> new ResourceNotFoundException("Категория "+productDto.getCategoryTitle()+" не найдена"));
            product.setCategory(category);
        }
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
//        save(product); сработает автоматически если были изменены
//        данные product, и прописана @Transactional.


    }

}
