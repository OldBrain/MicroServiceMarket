package ru.geekbrains.trainingproject.market.services.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.repositories.ProductRepository;
import ru.geekbrains.trainingproject.market.dtos.soap.ProductsSoapDto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSoapService {
    private final ProductRepository productRepository;

    public static final Function<Product, ProductsSoapDto> functionEntityToSoap = se -> {
        ProductsSoapDto s = new ProductsSoapDto();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setPrice(se.getPrice());
        return s;
    };

    public List<ProductsSoapDto> getAllProductSoapDto() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ProductsSoapDto getByTitle(String title) {
        return productRepository.findProductByTitleEquals(title).map(functionEntityToSoap).get();
    }
}
