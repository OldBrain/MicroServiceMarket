package ru.geekbrains.trainingproject.market.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.trainingproject.market.dtos.OrderItemDto;
import ru.geekbrains.trainingproject.market.services.CartService;
import ru.geekbrains.trainingproject.market.utils.Cart;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("")
    public List<OrderItemDto> getCartForCurrentUser() {
     return cartService.getCartForCurrentUser();
    }

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable Long productId) {
        cartService.addItem(productId);
    }

    @GetMapping("/decrement/{productId}")
    public void decrementItem(@PathVariable Long productId) {
        cartService.decrementItem(productId);
    }

    @GetMapping("/remove/{productId}")
    public void removeItem(@PathVariable Long productId) {
        cartService.removeItem(productId);
    }
}
