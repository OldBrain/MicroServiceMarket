package ru.geekbrains.trainingproject.market.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.geekbrains.trainingproject.market.dtos.OrderItemDto;
import ru.geekbrains.trainingproject.market.model.Product;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private int totalPrice;
    @JsonIgnore
    private ConcurrentHashMap<String, List<OrderItemDto>> cartMap;
    @JsonIgnore
    private TmpUserIdFromHttpRequestUtil tmpUserIdHttpRequest;

    public Cart(TmpUserIdFromHttpRequestUtil tmpUserIdHttpRequest) {
        this.tmpUserIdHttpRequest = tmpUserIdHttpRequest;
        cartMap = new ConcurrentHashMap();
    }

    @JsonIgnore
    public TmpUserIdFromHttpRequestUtil getTmpUserIdHttpRequest() {
        return null;
    }

    public boolean add(Long productId) {
        setCurrentItemList();
        for (OrderItemDto i : items) {
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
        items.add(new OrderItemDto(product));
        recalculate();
        addToCartMap();
    }

    public void decrement(Long productId) {
        setCurrentItemList();
        Iterator<OrderItemDto> iter = items.iterator();
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
        setCurrentItemList();
        items.removeIf(i -> i.getProductId().equals(productId));
        recalculate();
        addToCartMap();
    }

    public void clear() {
        setCurrentItemList();
        items.clear();
        totalPrice = 0;
        cartMap.remove(getCurrentUserTmpId());
    }

    private void recalculate() {
        totalPrice = 0;
        for (OrderItemDto i : items) {
            totalPrice += i.getPrice();
        }
    }

    private void addToCartMap() {
        cartMap.put(getCurrentUserTmpId(), items);
    }

    private List<OrderItemDto> setCurrentItemList() {
        if (cartMap.containsKey(getCurrentUserTmpId())) {
            items = cartMap.get(getCurrentUserTmpId());
        } else {
            items = new ArrayList<>();
        }
        return items;
    }

    private String getCurrentUserTmpId() {
        return tmpUserIdHttpRequest.getUserTmpId();
    }

//    public List<OrderItemDto> getCartByUserTmpId() {
//        items = cartMap.get(getCurrentUserTmpId());
//        return items;
//    }


}
