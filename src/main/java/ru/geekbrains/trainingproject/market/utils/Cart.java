package ru.geekbrains.trainingproject.market.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import ru.geekbrains.trainingproject.market.dtos.CartItemDto;
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
    private List<CartItemDto> items;
    private int totalPrice;
    private int pricePerProduct;

//    @JsonIgnore
//    private ConcurrentHashMap<String, List<CartItemDto>> cartMap;
    @JsonIgnore
    private UserDataFromHttpRequestUtil userDataFromHttpRequestUtil;
    @JsonIgnore
    private RedisTemplate redisTemplate;

    public Cart(UserDataFromHttpRequestUtil userDataFromHttpRequestUtil,RedisTemplate redisTemplate) {
        this.userDataFromHttpRequestUtil = userDataFromHttpRequestUtil;
//        cartMap = new ConcurrentHashMap();
        this.redisTemplate = redisTemplate;
    }

    public boolean add(Long productId) {
        setCurrentItemList();
        for (CartItemDto i : items) {
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
        items.add(new CartItemDto(product));
        addToCartMap();
        recalculate();
    }

    public void decrement(Long productId) {
        setCurrentItemList();
        Iterator<CartItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            CartItemDto i = iter.next();
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
        redisTemplate.delete(getCurrentKeyForCart());
    }

    public void recalculate() {
        setCurrentItemList();
        totalPrice = 0;
        for (CartItemDto i : items) {
            totalPrice += i.getPrice();
        }
    }

    private void addToCartMap() {
        redisTemplate.opsForValue().set(getCurrentKeyForCart(), items);
    }

    private List<CartItemDto> setCurrentItemList() {
        if (redisTemplate.hasKey(getCurrentKeyForCart())) {
            items = (List<CartItemDto>) redisTemplate.opsForValue().get(getCurrentKeyForCart());
        } else {
            items = new ArrayList<>();
        }
        return items;
    }

    public List<CartItemDto> getItemListByUserName(String userName) {
        if (userName != null) {
            return (List<CartItemDto>)redisTemplate.opsForValue().get(userName);
        } else {
            throw new ResourceNotFoundException("Корзина для "+userName+" не найдена");
        }
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
        if (redisTemplate.hasKey(getCurrentUserName()) && redisTemplate.hasKey(getCurrentTmpId())) {
            List<CartItemDto> tmpListByNameKey =(List<CartItemDto>) redisTemplate.opsForValue().get(getCurrentUserName());
            List<CartItemDto> tmpListByTmpId = (List<CartItemDto>) redisTemplate.opsForValue().get(getCurrentTmpId());
            List<CartItemDto> resultItemList = Stream.concat(
                    tmpListByNameKey.parallelStream(),
                    tmpListByTmpId.parallelStream()).collect(Collectors.toList());
            redisTemplate.delete(getCurrentTmpId());
            List<CartItemDto> sortedList = resultItemList.stream().sorted(Comparator.comparing(CartItemDto::getProductId)).collect(Collectors.toList());

            items = mergeItemList(sortedList);

            redisTemplate.opsForValue().set(getCurrentUserName(), items);

        }
        if (!redisTemplate.hasKey(getCurrentUserName()) && redisTemplate.hasKey(getCurrentTmpId())) {
            List<CartItemDto> tmpListByTmpId =(List<CartItemDto>) redisTemplate.opsForValue().get(getCurrentTmpId());
            redisTemplate.delete(getCurrentTmpId());
            redisTemplate.opsForValue().set(getCurrentUserName(), tmpListByTmpId);
        }
    }

    private List<CartItemDto> mergeItemList(List<CartItemDto> itemList) {
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
