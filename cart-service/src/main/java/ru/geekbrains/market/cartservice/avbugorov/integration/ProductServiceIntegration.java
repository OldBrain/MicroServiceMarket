package ru.geekbrains.market.cartservice.avbugorov.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.market.api.dtos.modeldtos.ProductDto;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;


    @Value("${integration.product-service.url}")
    private String productServiceUrl;

    public ProductDto getProductById(Long productId) {
        return restTemplate.getForObject(productServiceUrl + "/api/v1/products/" + productId, ProductDto.class);
    }
}
