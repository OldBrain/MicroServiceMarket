package ru.geekbrains.trainingproject.market.utils;

import lombok.Data;
import ru.geekbrains.trainingproject.market.config.security.JwcRequestFilter;
import ru.geekbrains.trainingproject.market.dtos.OrderItemDto;
import ru.geekbrains.trainingproject.market.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Data
public class Cart {
    private List<OrderItemDto> listItems;
    private int totalPrice;
    private ConcurrentHashMap<String, List<OrderItemDto>> cartMap = new ConcurrentHashMap();
    private JwcRequestFilter jwcRequestFilter;

    public Cart(JwcRequestFilter jwcRequestFilter) {
        this.jwcRequestFilter = jwcRequestFilter;
    }

    public Cart() {
    }

    public boolean add(Long productId) {
        for (OrderItemDto i : getCurrentItemList()) {
            if (i.getProductId().equals(productId)) {
                i.changeQuantity(1);
                recalculate();
                addToCartMap();
                return true;
            }
        }
        return false;
    }

    public void add(Product product) {
        getCurrentItemList().add(new OrderItemDto(product));
        recalculate();
        addToCartMap();
    }

    public void decrement(Long productId) {
        Iterator<OrderItemDto> iter = getCurrentItemList().iterator();
        while (iter.hasNext()) {
            OrderItemDto i = iter.next();
            if (i.getProductId().equals(productId)) {
                i.changeQuantity(-1);
                if (i.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                break;
            }
        }
        addToCartMap();
    }

    public void remove(Long productId) {
        getCurrentItemList().removeIf(i -> i.getProductId().equals(productId));
        recalculate();
        addToCartMap();
    }

    public void clear() {
        getCurrentItemList().clear();
        totalPrice = 0;
        cartMap.remove(getCurrentUserTmpId());
    }

    private void recalculate() {
        totalPrice = 0;
        for (OrderItemDto i : getCurrentItemList()) {
            totalPrice += i.getPrice();
        }
    }

    private void addToCartMap() {
        cartMap.put(getCurrentUserTmpId(), getCurrentItemList());
    }

    private List<OrderItemDto> getCurrentItemList() {
        if (cartMap.containsKey(getCurrentUserTmpId())) {
            return cartMap.get(getCurrentUserTmpId());
        } else {
            return new ArrayList<>();
        }
    }

    private String getCurrentUserTmpId() {
        return jwcRequestFilter.getUserTmpId();
    }

    public Cart getCartByUserTmpId() {
        listItems = cartMap.get(getCurrentUserTmpId());
        return this;
    }

}
