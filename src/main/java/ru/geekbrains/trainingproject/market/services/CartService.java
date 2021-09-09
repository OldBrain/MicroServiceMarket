package ru.geekbrains.trainingproject.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.geekbrains.trainingproject.market.config.security.JwcRequestFilter;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Product;
import ru.geekbrains.trainingproject.market.utils.Cart;

import javax.annotation.PostConstruct;
import java.security.Principal;

//@Service
@Component
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final JwcRequestFilter jwcRequestFilter;
    private Cart cart;
    String userName;


//    public CartService(String userName) {
//        this.userName = userName;
//        this.cart = new Cart(userName);
//    }

    @PostConstruct
    public void init() {
        this.userName = getUserNameFromSecurityContext();
        this.cart = new Cart(userName);
        System.out.println("ИМЯ: "+userName);
    }

    public Cart getCartForCurrentUser(String userTmpId) {
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
    private String getUserNameFromSecurityContext() {
        if (jwcRequestFilter.getCurrentUserName() != null) {
            return jwcRequestFilter.getCurrentUserName();
        }
        return "Host";
    }
}
