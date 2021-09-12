package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.utils.Cart;
import ru.geekbrains.trainingproject.market.utils.UserDataFromHttpRequestUtil;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final UserDataFromHttpRequestUtil tmpUserIdFromHttpRequest;
    private Cart cart;

    @PostConstruct
    public void init() {
        this.cart = new Cart(tmpUserIdFromHttpRequest);
    }

    public Cart getCartForCurrentUser() {
        cart.setCurrentItemList();
        return cart;
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

    public Integer getTotalSum() {
        return cart.getTotalPrice();
    }
}
