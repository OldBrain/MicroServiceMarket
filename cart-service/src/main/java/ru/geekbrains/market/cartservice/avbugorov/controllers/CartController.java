package ru.geekbrains.market.cartservice.avbugorov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.api.dtos.modeldtos.ProductDto;
import ru.geekbrains.market.cartservice.avbugorov.integration.ProductServiceIntegration;
import ru.geekbrains.market.cartservice.avbugorov.utils.Cart;
import ru.geekbrains.market.cartservice.avbugorov.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final ProductServiceIntegration productServiceIntegration;
//    private final ProductServiceIntegration productServiceIntegration;

    @GetMapping("")
    public Cart getCartForCurrentUser() {
        return cartService.getCartForCurrentUser();
    }

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable Long productId) {
    cartService.addItem(productServiceIntegration.getProductById(productId));
    }

    @GetMapping("/clear")
    public void clear() {
        cartService.clearCart();
    }

    @GetMapping("/decrement/{productId}")
    public void decrementItem(@PathVariable Long productId) {
        cartService.decrementItem(productId);
    }

    @GetMapping("/remove/{productId}")
    public void removeItem(@PathVariable Long productId) {
        cartService.removeItem(productId);
    }

    @GetMapping("/sum/")
    public Integer getTotalSum() {
        return cartService.getTotalSum();
    }
}
