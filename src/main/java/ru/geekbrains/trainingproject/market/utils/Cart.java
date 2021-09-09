package ru.geekbrains.trainingproject.market.utils;

import lombok.Data;
import ru.geekbrains.trainingproject.market.dtos.OrderItemDto;
import ru.geekbrains.trainingproject.market.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Cart {
    private String userTmpId;
    private List<OrderItemDto> listItems;
    private int totalPrice;
    ConcurrentHashMap<String,List<OrderItemDto>> cartMap = new ConcurrentHashMap();

    public Cart(String userTmpId) {
        this.userTmpId = userTmpId;

        if (cartMap.containsKey(userTmpId)) {
            this.listItems = cartMap.get(userTmpId);
        } else {
            this.listItems = new ArrayList<>();
        }
    }

    public boolean add(Long productId) {
        for (OrderItemDto i : listItems) {
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
        listItems.add(new OrderItemDto(product));
        recalculate();
        addToCartMap();
    }

    public void decrement(Long productId) {
        Iterator<OrderItemDto> iter = listItems.iterator();
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
        listItems.removeIf(i -> i.getProductId().equals(productId));
        recalculate();
        addToCartMap();
    }

    public void clear() {
        listItems.clear();
        totalPrice = 0;
        cartMap.remove(userTmpId);
    }

    private void recalculate() {
        totalPrice = 0;
        for (OrderItemDto i : listItems) {
            totalPrice += i.getPrice();
        }
    }
    private void addToCartMap() {
        cartMap.put(userTmpId, listItems);
    }

    public List<OrderItemDto> getCartByUserName(String userName) {
        return cartMap.get(userName);
    }

}
