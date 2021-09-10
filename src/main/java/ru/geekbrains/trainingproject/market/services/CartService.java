package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.config.security.JwcRequestFilter;
import ru.geekbrains.trainingproject.market.dtos.OrderItemDto;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final JwcRequestFilter jwcRequestFilter;
    private Cart cart;

    @PostConstruct
    public void init() {
        this.cart = new Cart(jwcRequestFilter);
    }

    public List<OrderItemDto> getCartForCurrentUser() {
        return cart.getCartByUserTmpId();
    }

    public void addItem(Long productId) {
        if (cart.add(productId)) {
            return;
        }
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину, так как продукт с id: " + productId + " не существует"));
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

}
