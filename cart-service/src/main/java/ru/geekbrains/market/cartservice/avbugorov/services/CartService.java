package ru.geekbrains.market.cartservice.avbugorov.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.api.dtos.modeldtos.ProductDto;
import ru.geekbrains.market.cartservice.avbugorov.integration.ProductServiceIntegration;
import ru.geekbrains.market.cartservice.avbugorov.utils.Cart;
import ru.geekbrains.market.cartservice.avbugorov.utils.UserDataFromHttpRequestCartUtil;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final UserDataFromHttpRequestCartUtil tmpUserIdFromHttpRequest;

    private Cart cart;
    @Value("${utils.cart.prefix}")
    private String cartPrefix;

    @PostConstruct
    public void init() {
        this.cart = new Cart(tmpUserIdFromHttpRequest, redisTemplate);
    }


    public Cart getCartForCurrentUser() {
        cart.setCurrentItemList();
        return cart;
    }

    public void addItem(ProductDto product) {

        cart.add(product);
    }

    public void removeItem(Long productId) {
        cart.remove(productId);
    }

    public void decrementItem(Long productId) {
        cart.decrement(productId);
    }

    public void clearCart() {
        cart.clear();
    }

    public Integer getTotalSum() {
        return cart.getTotalPrice();
    }


}
