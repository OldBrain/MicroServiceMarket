package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
public Page<Product> findAllPage(int pageIndex,int pageSize) {
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


    public List<Product> findAllByPriceIsBetween(Integer minPrise, Integer maxPrice) {
        return productRepository.findAllByPriceIsBetween(maxPrice, maxPrice);
    }

    public List<Product> findAllByPriceLessThanEqual(Integer maxPrice) {
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }

    public List<Product> findAllByPriceIsMoreThenEqual(Integer minPrice) {
        return productRepository.findAllByPriceIsMoreThenEqual(minPrice);
    }

}
