package ru.geekbrains.trainingproject.market.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.geekbrains.trainingproject.market.dtos.OrderItemDto;
import ru.geekbrains.trainingproject.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.trainingproject.market.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
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

    public void recalculate() {
        setCurrentItemList();
        totalPrice = 0;
        for (OrderItemDto i : items) {
            totalPrice += i.getPrice();
        }
    }

    private void addToCartMap() {
        cartMap.put(getCurrentKeyForCart(), items);
    }

    private List<OrderItemDto> setCurrentItemList() {
        if (cartMap.containsKey(getCurrentKeyForCart())) {
            items = cartMap.get(getCurrentKeyForCart());
        } else {
            items = new ArrayList<>();
        }
        return items;
    }

    private String getCurrentTmpId() {
        return userDataFromHttpRequestUtil.getUserTmpId();
    }

    private String getCurrentUserName() {
        return userDataFromHttpRequestUtil.getUserName();
    }

    private String getCurrentKeyForCart() {
        if (getCurrentUserName() != null) {
            reorganizeCurtMap();
            return getCurrentUserName();
        } else if (getCurrentTmpId() != null) {
            return getCurrentTmpId();
        }
        throw new ResourceNotFoundException("Не удалось получить данные о клиенте из httpServlet");
    }

    private void reorganizeCurtMap() {
        if (cartMap.containsKey(getCurrentUserName()) && cartMap.containsKey(getCurrentTmpId())) {
            List<OrderItemDto> tmpListByNameKey = cartMap.get(getCurrentUserName());
            List<OrderItemDto> tmpListByTmpId = cartMap.get(getCurrentTmpId());
            List<OrderItemDto> resultItemList = Stream.concat(
                    tmpListByNameKey.parallelStream(),
                    tmpListByTmpId.parallelStream()).collect(Collectors.toList());
            cartMap.remove(getCurrentTmpId());
            List<OrderItemDto> sortedList = resultItemList.stream().sorted(Comparator.comparing(OrderItemDto::getProductId)).collect(Collectors.toList());

            items = mergeItemList(sortedList);

            cartMap.put(getCurrentUserName(), items);
        }
        if (!cartMap.containsKey(getCurrentUserName()) && cartMap.containsKey(getCurrentTmpId())) {
            List<OrderItemDto> tmpListByTmpId = cartMap.get(getCurrentTmpId());
            cartMap.remove(getCurrentTmpId());
            cartMap.put(getCurrentUserName(), tmpListByTmpId);
        }
    }

    private List<OrderItemDto> mergeItemList(List<OrderItemDto> itemList) {
        for (int i = 0; i < itemList.size() - 1; i++) {
            if (itemList.get(i).getProductId().
                    equals(itemList.get(i + 1).getProductId())) {

                itemList.get(i).setQuantity(itemList.get(i).getQuantity()
                        + itemList.get(i + 1).getQuantity());
                itemList.remove(i + 1);
                i = 0;
            }
        }
        return itemList;
    }

    public int getTotalPrice() {
        setCurrentItemList();
        recalculate();
        return totalPrice;
    }
}
