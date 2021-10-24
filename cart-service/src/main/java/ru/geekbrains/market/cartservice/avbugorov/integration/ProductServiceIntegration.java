package ru.geekbrains.market.cartservice.avbugorov.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.market.api.dtos.modeldtos.ProductDto;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final WebClient productServiceWebClient;



    public ProductDto getProductById(Long productId) {

        ProductDto product = productServiceWebClient.get()
                .uri("/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
        return product;
    }
}
