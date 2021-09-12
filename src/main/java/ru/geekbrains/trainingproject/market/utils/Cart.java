package ru.geekbrains.trainingproject.market.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.geekbrains.trainingproject.market.dtos.OrderItemDto;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private int totalPrice;
    @JsonIgnore
    private ConcurrentHashMap<String, List<OrderItemDto>> cartMap;
    @JsonIgnore
    private UserDataFromHttpRequestUtil userDataFromHttpRequestUtil;

    public Cart(UserDataFromHttpRequestUtil userDataFromHttpRequestUtil) {
        this.userDataFromHttpRequestUtil = userDataFromHttpRequestUtil;
        cartMap = new ConcurrentHashMap();
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
        setCurrentItemList();
        items.add(new OrderItemDto(product));
        addToCartMap();
        recalculate();
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
        cartMap.remove(getCurrentKeyForCart());
    }

//    public void changeKeyToUserName() {
//        if (!isNameAlreadyFoundInCartMap() && isUserTmpIdAlreadyFoundInCartMap()) {
//            List<OrderItemDto> tmpIdItemList = cartMap.get(getCurrentUserTmpId());
//            cartMap.remove(getCurrentUserTmpId());
//            cartMap.put(getCurrentUserName(), tmpIdItemList);
//        } else if(isNameAlreadyFoundInCartMap() && isUserTmpIdAlreadyFoundInCartMap())
//        {
//            List<OrderItemDto> tmpIdItemList = cartMap.get(getCurrentUserTmpId());
//            List<OrderItemDto> userNameItemList = cartMap.get(getCurrentUserTmpId());
//            items = Stream.concat(
//                    userNameItemList.parallelStream(),
//                    tmpIdItemList.parallelStream()).
//                    collect(Collectors.toList());
//            cartMap.put(getCurrentUserName(), items);
//        }
//
//    }

    private void recalculate() {
        setCurrentItemList();
        totalPrice = 0;
        for (OrderItemDto i : items) {
            totalPrice += i.getPrice();
        }
    }

    private void addToCartMap() {
//        System.out.println(getCurrentKeyForCart());
        cartMap.put(getCurrentKeyForCart(), items);
    }

    public List<OrderItemDto> setCurrentItemList() {
        if (cartMap.containsKey(getCurrentKeyForCart())) {
            items = cartMap.get(getCurrentKeyForCart());
        } else {
            items = new ArrayList<>();
        }
        return items;
    }

    private String getCurrentUserTmpId() {
        return userDataFromHttpRequestUtil.getUserTmpId();
    }

    private String getCurrentUserName() {
        return userDataFromHttpRequestUtil.getUserName();
    }

    private String getCurrentKeyForCart() {
        if (getCurrentUserName() != null) {
            return getCurrentUserName();
        } else if (getCurrentUserTmpId()!=null) {
            return getCurrentUserTmpId();
        }
        throw new ResourceNotFoundException("Не удалось получить данные о клиенте из httpServlet");
    }

    public int getTotalPrice() {
        setCurrentItemList();
        recalculate();
        return totalPrice;
    }
}
